package com.demo.service;

import java.util.List;

import com.demo.bean.Chair;

public interface ChairService {

    List<Chair> getByFloorID(String floorId);

    Chair getByCordXAndCordYAndFloorId(int i, int j, String floorId);

}
