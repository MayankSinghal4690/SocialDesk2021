package com.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Employee;
import com.demo.bean.LoginResponseEntity;
import com.demo.dao.LoginDao;

@Service
@Transactional
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	LoginDao loginDao;

	@Override
	public LoginResponseEntity login(String email) {
		
		LoginResponseEntity loginResponseEntity = new LoginResponseEntity();
		
		Employee emp = loginDao.findByEmail(email);
		if(emp==null)
		{
			loginResponseEntity.setRole("NULL");
			loginResponseEntity.setStatus("Error");
		}
		else if(emp.getEmail().equals("admin@gmail.com"))
		{
			loginResponseEntity.setRole("Admin");
			loginResponseEntity.setStatus("Success");
		}
		else
		{
			loginResponseEntity.setRole("Non-admin");
			loginResponseEntity.setStatus("Success");
		}
		return loginResponseEntity;
	}

}

