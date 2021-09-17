package com.demo.cronjobs;

import com.demo.bean.Booking;
import com.demo.bean.Floor;
import com.demo.dao.BookingDao;
import com.demo.service.MailerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@Component
@EnableAsync
public class MailerJob {

    @Autowired
    BookingDao bookingDao;

    private static final Logger log = LoggerFactory.getLogger(MailerJob.class);

    @Async
    @Scheduled(cron = "0 0 18 * * *")
    public void reportCurrentTime() {
        log.info("Running mailer cron job");
        Calendar cStart = Calendar.getInstance();
        cStart.setTime(new Date());
        cStart.add(Calendar.DATE, 1);

        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(new Date());
        cEnd.add(Calendar.DATE, 2);

        List<Booking> notifyUser = bookingDao.findByStartdateBetween(cStart.getTime(),cEnd.getTime());
        log.info("Got a total of "+notifyUser.size()+" to be notified using email");
        notifyUser.forEach(this::notifyUser);

    }

    private String getEmailTemplate() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("emailReminder.html");
        byte[] binaryData = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
        return new String(binaryData, StandardCharsets.UTF_8);
    }



    private void notifyUser(Booking booking){
        try {
            log.info("Generating mail for booking "+booking.getId());
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String emailTemplate = getEmailTemplate();
            log.info("Found email template as "+emailTemplate);
            emailTemplate = emailTemplate.replace("PERSON_NAME",booking.getEmployee().getEmpname());
            emailTemplate = emailTemplate.replace("DESK_NO",booking.getChair().getId());
            Floor floor = booking.getFloor();
            emailTemplate = emailTemplate.replace("OFFICE",floor.getId()+"/"+floor.getOffice().getName());
            emailTemplate = emailTemplate.replace("START_DATE",dateFormat.format(booking.getStartdate()));
            emailTemplate = emailTemplate.replace("END_DATE",dateFormat.format(booking.getEnddate()));
            log.info("Sending email to user as "+emailTemplate);
            MailerService.getInstance().sendHTML(booking.getEmployee().getEmail().toLowerCase(),"Reminder for your upcoming booking",emailTemplate);
            log.info("User reminded for mail "+booking);
        }catch (Exception e){

            log.error("Error sending mail to user "+e.getLocalizedMessage());
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("getBookingReminder/{bookingId}")
    public String sendReminder(@PathVariable String bookingId){
        try {
            Booking one = bookingDao.getOne(Integer.parseInt(bookingId));
            notifyUser(one);
            return one.getEmployee().getEmail();
        }catch (Exception e){

            return "error "+e;
        }
    }

}
