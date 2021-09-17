package com.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bean.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer> {

    Employee findByEmail(String email);

    List<Employee> findByteamid(String teamid);
}
