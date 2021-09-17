package com.demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bean.Booking;
import com.demo.bean.Chair;
import com.demo.bean.ChairObj;
import com.demo.bean.Employee;
import com.demo.bean.Detail;
import com.demo.bean.Location;

@RestController
public class SearchDeskController {

    @Autowired
    LocationService locationService;
    @Autowired
    OfficeService officeService;
    @Autowired
    FloorService floorService;
    @Autowired
    ChairService chairService;
    @Autowired
    BookingService bookingService;
    @Autowired
    EmployeeService empService;
    @CrossOrigin(origins = "*")
    @GetMapping("/viewLocation")
    public List<Location> displayAllLocation() {

        return locationService.getAllLocation();
    }


    //added for postmapping requestbody
    @CrossOrigin(origins = "*")
    @PostMapping("/desks/{email}")
    public List<ChairObj> displayAllDesk(@RequestBody Detail detail, @PathVariable String email) {
        System.out.println(detail);

        String floorId = detail.getOfficeName().substring(0, 3) + detail.getFloorNo();

        List<Chair> availableChair = chairService.getByFloorID(floorId);
        List<Booking> booking = bookingService.getAllBookings();
        List<ChairObj> finalStatuschair = socialDistancing(availableChair, booking, detail.getStartDate(), detail.getEndDate(), email,floorId);
        for (int i = 0; i < finalStatuschair.size(); i++) {
            System.out.println(finalStatuschair.get(i));
        }
        System.out.println("Chairs");

        return finalStatuschair;

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/desks/{email}")
    public List<ChairObj> displayAllDesk(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                         @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
                                         @RequestParam("officeName") String officeName,
                                         @RequestParam("floorNo") int floorNo,
                                         @PathVariable String email) {


        String floorId = officeName.substring(0, 3) + floorNo;

        List<Chair> availableChair = chairService.getByFloorID(floorId);
        List<Booking> booking = bookingService.getAllBookings();
        List<ChairObj> finalStatuschair = socialDistancing(availableChair, booking, startDate, endDate, email,floorId);
        for (int i = 0; i < finalStatuschair.size(); i++) {
            System.out.println(finalStatuschair.get(i));
        }
        System.out.println("Chairs");
        return finalStatuschair;

    }
    public int containsId( List<ChairObj> list, String id){
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getId().equals(id))
                return i;
        }
        return -1;
    }



    private List<ChairObj> socialDistancing(List<Chair> ch, List<Booking> bookstatus, Date dates, Date datee, String email,String floorId) {
        List<ChairObj> chairstatus = new ArrayList<ChairObj>();
        Employee employee = empService.getByEmail(email);
        for (int i = 0; i < bookstatus.size(); i++) {

            if(!bookstatus.get(i).getFloor().getId().equals(floorId))
                continue;
            Date lastBooked = bookstatus.get(i).getEnddate();
            Date startBooked = bookstatus.get(i).getStartdate();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String datelastBooked = formatter.format(lastBooked);
            System.out.println(datelastBooked);
            String datestartBooked = formatter.format(startBooked);
            System.out.println(datestartBooked);


            String datestart = formatter.format(dates);
            System.out.println(datestart);
            String dateend = formatter.format(datee);
            System.out.println(dateend);
            if (datestart.compareTo(datelastBooked) > 0 || dateend.compareTo(datestartBooked) < 0) {

                if(containsId(chairstatus,bookstatus.get(i).getChair().getId())==-1)
                    chairstatus.add(new ChairObj(bookstatus.get(i).getChair().getId(), bookstatus.get(i).getChair().getCordx(), bookstatus.get(i).getChair().getCordy(), bookstatus.get(i).getChair().getRotation(), "chair", "null"));
            }
            else {

                if (bookstatus.get(i).getDeskstatus().equals("booked")) {
                    String team = bookstatus.get(i).getEmployee().getTeamid();
                    String tid = employee.getTeamid();
                    String teamMember = bookstatus.get(i).getEmployee().getEmpname();
                    if (team.equals(tid)) {

                        System.out.println("equals");
                        if(containsId(chairstatus,bookstatus.get(i).getChair().getId())!=-1) {
                            int index=containsId(chairstatus,bookstatus.get(i).getChair().getId());
                            chairstatus.get(index).setStatus("chair-na");
                            chairstatus.get(index).setTeammembername(teamMember);

                        }
                        else
                            chairstatus.add(new ChairObj(bookstatus.get(i).getChair().getId(), bookstatus.get(i).getChair().getCordx(), bookstatus.get(i).getChair().getCordy(), bookstatus.get(i).getChair().getRotation(), "chair-na", teamMember));

                    } else {
                        System.out.println("Not equals");
                        if(containsId(chairstatus,bookstatus.get(i).getChair().getId())!=-1) {
                            int index=containsId(chairstatus,bookstatus.get(i).getChair().getId());
                            chairstatus.get(index).setStatus("chair-na");

                        }
                        else
                            chairstatus.add(new ChairObj(bookstatus.get(i).getChair().getId(), bookstatus.get(i).getChair().getCordx(), bookstatus.get(i).getChair().getCordy(), bookstatus.get(i).getChair().getRotation(), "chair-na", "null"));

                    }
                    //add adjacent to block if available and do not copy them
                    List<ChairObj> added = adjacentChairs(chairstatus, bookstatus.get(i), email,floorId);

                    chairstatus.addAll(added);

                }
                else {
                    //blocked
                    chairstatus.add(new ChairObj(bookstatus.get(i).getChair().getId(), bookstatus.get(i).getChair().getCordx(), bookstatus.get(i).getChair().getCordy(), bookstatus.get(i).getChair().getRotation(), "chair-na", "null"));

                }
            }

        }
        //available and not present in chairstatus and not present in booking
        for (int i = 0; i < ch.size(); i++) {
            List<Booking> booking = bookingService.findByChairId(ch.get(i).getId());
            if (booking.isEmpty() && isPresent(chairstatus, ch.get(i).getId()) == false)
                chairstatus.add(new ChairObj(ch.get(i).getId(), ch.get(i).getCordx(), ch.get(i).getCordy(), ch.get(i).getRotation(), "chair", "null"));


        }


        return chairstatus;
    }



    private boolean isPresent(List<ChairObj> chairstatus, String id) {
        for (int i = 0; i < chairstatus.size(); i++) {
            if (chairstatus.get(i).getId().equals(id))
                return true;
        }
        return false;
    }

    private List<ChairObj> adjacentChairs(List<ChairObj> chairstatus, Booking bookstatus, String email,String floorId) {
        List<ChairObj> added = new ArrayList<ChairObj>();

        int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};


        for (int i = 0; i < 8; i++) {
            int cordx = bookstatus.getChair().getCordx();
            int cordy = bookstatus.getChair().getCordy();
            //this means present in available
            Chair chair = chairService.getByCordXAndCordYAndFloorId(cordx + dir[i][0], cordy + dir[i][1], floorId);
            if (chair != null && chair.getFloor().getId().equals(floorId)) {

                ChairObj check = new ChairObj(chair.getId(), chair.getCordx(), chair.getCordy(), chair.getRotation(), "chair-na", "null");

                if (isPresent(chairstatus, check.getId()) == false){
                    added.add(check);
                }
//                added this new code block
                else {
                    try {
                        int index = containsId(chairstatus, check.getId());
                        if (index != -1)
                            chairstatus.get(index).setStatus("chair-na");
                    }
                    catch(Exception e){
                        System.out.println("ERROR");
                    }
                }
//                added this new code block
            }
        }

        return added;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/desks/{email}/book")
    public ResponseEntity<Object> newBooking(@RequestBody NewBookingRequest newBookingRequest, @PathVariable String email) {

        String floorId = newBookingRequest.getOfficeName().substring(0, 3) + newBookingRequest.getFloorNo();
        System.out.println(floorId);

        Booking newBooking = new Booking();
        newBooking.setStartdate(newBookingRequest.getStartDate());
        newBooking.setEnddate(newBookingRequest.getEndDate());

        try {
            Booking completedBooking = bookingService.newChairBooking(email, floorId, newBookingRequest.getChairId(), newBooking);
            return new ResponseEntity<>(completedBooking, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("error " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping("/bookings/{email}/{bookingId}")
    public ResponseEntity<String> deleteBookingWithId(@PathVariable String email, @PathVariable int bookingId) {
        try {
            bookingService.deleteBooking(bookingId);
            return new ResponseEntity<>("success", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("error " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static class NewBookingRequest {

        private Date startDate;
        private Date endDate;
        private String officeName;
        private int floorNo;
        private String chairId;

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public String getOfficeName() {
            return officeName;
        }

        public void setOfficeName(String officeName) {
            this.officeName = officeName;
        }

        public int getFloorNo() {
            return floorNo;
        }

        public void setFloorNo(int floorNo) {
            this.floorNo = floorNo;
        }

        public String getChairId() {
            return chairId;
        }

        public void setChairId(String chairId) {
            this.chairId = chairId;
        }
    }
}




