package com.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.bean.Floor;

@Repository
public interface FloorDao extends JpaRepository<Floor, String> {

    List<Floor> findByOfficeId(String id);

    Floor getById(String floorId);
    
    List<Floor> findFloorByOfficeId(String id);
}
