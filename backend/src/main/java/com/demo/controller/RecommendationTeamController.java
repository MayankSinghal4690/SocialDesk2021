package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bean.Employee;
import com.demo.service.BookingService;
import com.demo.service.EmployeeService;

@RestController
public class RecommendationTeamController {
    @Autowired
    EmployeeService empService;
    @Autowired
    BookingService bookingService;
    @CrossOrigin(origins = "*")
    @GetMapping("/recommendfloor/{email}")
    public int getFloorRecommendation(@PathVariable String email) {
        Employee employee = empService.getEmployeeByEmail(email);
        return bookingService.getbookingTeam(employee.getTeamid());
    }
}
