package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Booking;
import com.demo.bean.Employee;
import com.demo.dao.BookingDao;
import com.demo.dao.EmployeeDao;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    BookingDao bookingDao;

    @Override
    public Employee getByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeDao.findAll();
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Override
    public List<Booking> getTeamInfo(String email) {
        Employee employee = employeeDao.findByEmail(email);
        List<Employee> employeeInfo = employeeDao.findByteamid(employee.getTeamid());

        List<Booking> booking = new ArrayList<Booking>();
        for (int i = 0; i < employeeInfo.size(); i++) {
            if (!employeeInfo.get(i).getEmail().equals(email))
                booking.addAll(bookingDao.findByEmployee(employeeInfo.get(i)));
            System.out.println(employeeInfo.get(i).getEmpname());

        }
        return booking;
    }

}
