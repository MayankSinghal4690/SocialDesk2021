package com.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.demo.bean.Chair;
import com.demo.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Booking;
import com.demo.bean.Employee;
import com.demo.bean.Floor;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingDao bookingDao;

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    FloorDao floorDao;

    @Autowired
    ChairDao chairDao;

    @Override
    public List<Booking> getAllBookings() {
        return bookingDao.findAll();
    }

    @Override
    public List<Booking> findByChairId(String id) {
        return bookingDao.findByChairId(id);
    }

    @Override
    public int getbookingTeam(String teamId) {
        List<Booking> allBookingDetails = bookingDao.findAll();
        String floorId;
        for (int i = 0; i < allBookingDetails.size(); i++) {
            if (allBookingDetails.get(i).getEmployee().getTeamid().equals(teamId)) {

                floorId = allBookingDetails.get(i).getChair().getFloor().getId();
                return floorId.charAt(floorId.length() - 1) - 48;
            }
        }
        return -1;
    }

    @Override
    public Booking newChairBooking(String email, String floorId, String chairId, Booking newBooking) throws Exception {

        Employee employee = employeeDao.findByEmail(email);
        List<Booking> existingBooking = bookingDao.findByEmployee(employee);
        Chair chair = chairDao.getById(chairId);
        Floor floor = floorDao.getById(floorId);

        if(floor.getStatus().equals("available")) {

            if(existingBooking.size()==0 || checkDatesBooked(existingBooking, newBooking)) {
                System.out.println(existingBooking.size());
                newBooking.setEmployee(employee);
                newBooking.setChair(chair);
                newBooking.setFloor(floor);
                newBooking.setDeskstatus("booked");
            } else {
                throw new Exception("Already booked a chair in specified date");
            }

            return bookingDao.save(newBooking);
        } else {
            throw new Exception("floor not available");
        }
    }

    boolean checkDatesBooked(List<Booking> existingBooking, Booking newBooking) {
        long counter = existingBooking.stream()
                .filter(record -> newBooking.getStartdate().compareTo(record.getEnddate())>0 ||
                        newBooking.getEnddate().compareTo(record.getStartdate())<0).count();

        System.out.println("previous bookings = " +counter);
        return counter == existingBooking.size();
    }

    @Override
    public void deleteBooking(int bookingId) throws Exception {

        try {
            Optional<Booking> booking = bookingDao.findById(bookingId);
            bookingDao.delete(booking.get());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}



