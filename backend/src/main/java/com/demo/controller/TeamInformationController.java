package com.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.demo.bean.Floor;
import com.demo.bean.Office;
import com.demo.bean.TeamInformation;
import com.demo.service.FloorService;
import com.demo.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bean.Booking;
import com.demo.service.BookingService;
import com.demo.service.EmployeeService;

@RestController
public class TeamInformationController {
    @Autowired
    EmployeeService employeeService;
@Autowired
FloorService floorService;
    @CrossOrigin(origins = "*")
    @GetMapping("/team/{email}")
    public List<TeamInformation> getTeamInformation(@PathVariable String email) {

      List<Booking> bookingteam= employeeService.getTeamInfo(email);
      List<TeamInformation> teamInformation=new ArrayList<TeamInformation>();
for(int i=0;i<bookingteam.size();i++)
{
    String floorId=bookingteam.get(i).getFloor().getId();
    int floorNo=floorId.charAt(floorId.length()-1)-48;
  String name=bookingteam.get(i).getFloor().getOffice().getName();
    teamInformation.add(new TeamInformation(bookingteam.get(i).getEmployee().getEmpname(),name,bookingteam.get(i).getEmployee().getTeamid()
    ,floorNo,bookingteam.get(i).getId(),bookingteam.get(i).getStartdate(),bookingteam.get(i).getEnddate()));
}
return teamInformation;
    }

}
