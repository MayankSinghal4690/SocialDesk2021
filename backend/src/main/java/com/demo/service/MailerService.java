package com.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@RestController
public class MailerService {
    private static final String user="team15db@zohomail.in";
    private static final String pass="TjXwq0xCh3tL";
    private static final String host="smtp.zoho.in";
    private static final String port="465";

    Logger logger = LoggerFactory.getLogger(MailerService.class);
    private static MailerService mailerService;


    public void sendMail(String to,String head, String body){
        try{
            MimeMessage message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(head);
            message.setText(body);
            Transport.send(message);
            logger.info("Sent email to "+to+" as "+message.toString());
        }catch (MessagingException mex) {mex.printStackTrace();}
    }

    public void sendHTML(String to,String head, String body){
        try{
            MimeMessage message = new MimeMessage(getSession());
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject(head);
            message.setContent(body,"text/html");
            Transport.send(message);
            logger.info("Sent email to "+to+" as "+message.toString());
        }catch (MessagingException mex) {mex.printStackTrace();}
    }

    private Session getSession(){
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);
        properties.put("mail.smtp.startssl.enable", "true");
        properties.setProperty("mail.pop3.socketFactory.class",  "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback","false");
        properties.setProperty("mail.smtp.socketFactory.port",port);
        properties.put("mail.smtp.startssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        return Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user,pass);
            }
        });
    }

    public static void main(String[] args) {
        getInstance().sendMeMail("maheshtamse.13@gmail.com");
    }

    private MailerService(){

    }

    @GetMapping("/getDemoMail/{email}")
    public String sendMeMail(@PathVariable String email){
        getInstance().sendMail(email,"This is a demo mail","Demo mail");
        return "Success";
    }

    public static MailerService getInstance(){
        if(mailerService==null)
            mailerService=new MailerService();
        return mailerService;
    }

}
