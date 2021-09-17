package com.demo.service;

import java.util.List;

import com.demo.bean.Location;

public interface LocationService {
    List<Location> getAllLocation();

    Location getByName(String name);

    Location getById(int id);
}
