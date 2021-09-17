package com.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.bean.Chair;

@Repository
public interface ChairDao extends JpaRepository<Chair, Integer> {
    List<Chair> findByFloorId(String id);

//    Chair findByCordxAndCordy(int i, int j);

    Optional<Chair> findById(String chairId);

    Chair getById(String chairId);

	Chair findByCordxAndCordyAndFloorId(int i, int j, String floorId);
}
