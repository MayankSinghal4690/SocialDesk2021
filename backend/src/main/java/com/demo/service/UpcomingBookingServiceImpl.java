package com.demo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Booking;
import com.demo.bean.Employee;
import com.demo.bean.UpcomingBooking;
import com.demo.bean.UpcomingBookingResponseEntity;
import com.demo.dao.LoginDao;
import com.demo.dao.UpcomingBookingDao;


@Service
@Transactional
public class UpcomingBookingServiceImpl implements UpcomingBookingService{

	@Autowired
	UpcomingBookingDao upcomingBookingDao;
	
	@Autowired
	LoginDao loginDao;

	@Override
	public UpcomingBookingResponseEntity getUpcomingBookings(String email) {
		
		UpcomingBookingResponseEntity response = new UpcomingBookingResponseEntity();
		
		Employee emp = loginDao.findByEmail(email);
		
		List<Optional<Booking>> bookingList = upcomingBookingDao.findByEmployee(emp);
		
		List<UpcomingBooking> booking = new ArrayList<>();
		
		Date todayDate = new Date();
		
		for(Optional<Booking> b: bookingList)
		{
			if(todayDate.before(b.get().getStartdate()) && b.get().getDeskstatus().equals("booked"))
			{
				UpcomingBooking ub= new UpcomingBooking();
				ub.setId(b.get().getId());
				ub.setDeskstatus(b.get().getDeskstatus());
				ub.setEnddate(b.get().getEnddate());
				ub.setStartdate(b.get().getStartdate());
				ub.setChairId(b.get().getChair().getId());
				ub.setFloorId(b.get().getFloor().getId());
				ub.setOfficeName(b.get().getFloor().getOffice().getName());
				booking.add(ub);
			}
			
		}
		
		if(!booking.isEmpty())
		{
			response.setBooking(booking);
			response.setStatus("Success");
		}else {
			response.setStatus("No upcoming Bookings!");
		}
		
		
		return response;
	}
	

}
