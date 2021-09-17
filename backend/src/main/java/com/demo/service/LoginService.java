package com.demo.service;

import com.demo.bean.Employee;
import com.demo.bean.LoginResponseEntity;

public interface LoginService {

    LoginResponseEntity login(String email);
}
