package com.demo.service;

import java.util.List;

import com.demo.bean.Booking;

public interface BookingService {

	List<Booking> getAllBookings();

	List<Booking> findByChairId(String id);

	int getbookingTeam(String string);

	Booking newChairBooking(String email, String floorId, String chairId, Booking newBooking) throws Exception;

	void deleteBooking(int bookingId) throws Exception;
}
