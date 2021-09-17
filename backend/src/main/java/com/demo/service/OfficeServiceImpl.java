package com.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.bean.Office;
import com.demo.dao.OfficeDao;

@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {
    @Autowired
    OfficeDao officeDao;

    @Override
    public Optional<Office> getByName(String name) {
        return officeDao.findByName(name);
    }

    @Override
    public List<Office> getAllOfficeByLocationId(int id) {
        return officeDao.findByLocationId(id);
    }

    @Override
    public List<Office> getAllOffice() {
        return officeDao.findAll();
    }

    @Override
    public List<Office> getAllOffice(int i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Office> getById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Office> getOffice() {
        return officeDao.findAll();
    }

}
