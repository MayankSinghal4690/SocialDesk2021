package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.demo.bean.LoginRequestEntity;
import com.demo.bean.LoginResponseEntity;
import com.demo.bean.UpcomingBookingResponseEntity;
import com.demo.service.UpcomingBookingService;

@RestController
public class UpcomingBookingController {
	
	@Autowired
	UpcomingBookingService upcomingBookingService;
	@CrossOrigin(origins = "*")
	@GetMapping("/upcomingbooking/{email}")
	public @ResponseBody UpcomingBookingResponseEntity getUpcomingBookings(@PathVariable String email){
		
		UpcomingBookingResponseEntity response = new UpcomingBookingResponseEntity();
		
		response = upcomingBookingService.getUpcomingBookings(email);
		
		return response;
	}

}
