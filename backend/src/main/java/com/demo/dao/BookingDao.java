package com.demo.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.bean.Booking;
import com.demo.bean.Employee;
import com.demo.bean.Location;

@Repository
public interface BookingDao extends JpaRepository<Booking, Integer> {

    List<Booking> findByChairId(String id);

    List<Booking> findByEmployee(Employee employee);

    List<Booking> findByStartdate(Date date);

    List<Booking> findByStartdateBetween(Date start,Date end);

}
