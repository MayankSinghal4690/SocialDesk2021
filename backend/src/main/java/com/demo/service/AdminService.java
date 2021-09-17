package com.demo.service;

import com.demo.bean.Floor;

public interface AdminService {

    Floor blockFloor(String floorId, String status);
}
