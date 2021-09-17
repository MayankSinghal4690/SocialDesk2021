package com.demo.service;

import java.util.List;

import com.demo.bean.Booking;
import com.demo.bean.Employee;

public interface EmployeeService {

	Employee getByEmail(String email);

	List<Employee> getAllEmployee();

	Employee getEmployeeByEmail(String email);

	List<Booking> getTeamInfo(String email);

}
