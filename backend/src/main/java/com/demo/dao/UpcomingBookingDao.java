package com.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.bean.Booking;
import com.demo.bean.Employee;
import com.demo.bean.UpcomingBooking;

public interface UpcomingBookingDao extends JpaRepository<Booking, Employee> {

    List<Optional<Booking>> findByEmployee(Employee emp);
}
