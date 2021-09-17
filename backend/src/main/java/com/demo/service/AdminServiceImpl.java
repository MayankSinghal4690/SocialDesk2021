package com.demo.service;

import com.demo.bean.Floor;
import com.demo.dao.FloorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private FloorDao floorDao;

    @Override
    public Floor blockFloor(String floorId, String status) {

        Floor currFloor = floorDao.getById(floorId);
        currFloor.setStatus(status);

        return floorDao.save(currFloor);
    }
}
