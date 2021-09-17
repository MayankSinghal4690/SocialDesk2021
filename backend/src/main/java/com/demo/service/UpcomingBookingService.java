package com.demo.service;

import com.demo.bean.UpcomingBookingResponseEntity;

public interface UpcomingBookingService {

    UpcomingBookingResponseEntity getUpcomingBookings(String email);

}
