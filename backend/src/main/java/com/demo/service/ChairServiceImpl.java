package com.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Chair;
import com.demo.dao.ChairDao;

@Service
@Transactional
public class ChairServiceImpl implements ChairService {

    @Autowired
    ChairDao chairDao;

    @Override
    public List<Chair> getByFloorID(String floorId) {

        return chairDao.findByFloorId(floorId);
    }

    @Override
    public Chair getByCordXAndCordYAndFloorId(int i, int j, String floorId) {

        return chairDao.findByCordxAndCordyAndFloorId(i, j, floorId);
    }


}
