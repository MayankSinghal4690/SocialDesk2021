package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.demo.bean.Employee;
import com.demo.bean.LoginRequestEntity;
import com.demo.bean.LoginResponseEntity;
import com.demo.service.LoginService;

@RestController
public class LoginController {
	
	@Autowired
	LoginService loginService;
	@CrossOrigin(origins = "*")
	@PostMapping("/login")
	public @ResponseBody LoginResponseEntity login(@RequestBody LoginRequestEntity loginRequestEntity){
		
		LoginResponseEntity response = new LoginResponseEntity();
		
		response = loginService.login(loginRequestEntity.getEmail());
		
		return response;
	}
	

}
