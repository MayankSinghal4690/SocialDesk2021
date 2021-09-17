package com.demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.bean.Office;

public interface OfficeService {
    List<Office> getAllOffice(int i);

    Optional<Office> getByName(String name);

    List<Office> getAllOffice();

    List<Office> getAllOfficeByLocationId(int id);

    Optional<Office> getById(String id);

    List<Office> getOffice();

}
