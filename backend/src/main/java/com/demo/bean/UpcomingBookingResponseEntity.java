package com.demo.bean;

import java.util.List;
import java.util.Optional;

public class UpcomingBookingResponseEntity {

    private String status;
    private List<UpcomingBooking> booking;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<UpcomingBooking> getBooking() {
        return booking;
    }

    public void setBooking(List<UpcomingBooking> booking) {
        this.booking = booking;
    }

    public UpcomingBookingResponseEntity() {
    }

    public UpcomingBookingResponseEntity(String status, List<UpcomingBooking> booking) {
        super();
        this.status = status;
        this.booking = booking;
    }

}
