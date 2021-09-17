package com.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.bean.Office;

@Repository
public interface OfficeDao extends JpaRepository<Office, String> {

    Optional<Office> findByName(String name);

    List<Office> findByLocationId(int id);

    Optional<Office> findById(String id);
}
