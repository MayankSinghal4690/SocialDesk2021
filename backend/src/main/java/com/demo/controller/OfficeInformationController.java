package com.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bean.Floor;
import com.demo.bean.FloorStatus;
import com.demo.bean.Office;
import com.demo.service.FloorService;
import com.demo.service.OfficeService;

@RestController
public class OfficeInformationController {
    @Autowired
    FloorService floorService;
    
    @Autowired
    OfficeService officeService;
    
    @CrossOrigin(origins = "*")
    @GetMapping("/office")
    public List<FloorStatus> getAllOffices() {
        
        List<Office> offices = officeService.getAllOffice();
        List<FloorStatus> floorstatus = new ArrayList<FloorStatus>();
        for (int i = 0; i < offices.size(); i++) {
        	List<Floor> floor = floorService.getFloorByOfficeId(offices.get(i).getId());
            int numFloors = offices.get(i).getNum();
            List<String> status = new ArrayList<>();
            for(int j=0;j<floor.size();j++)
            {
            	status.add(floor.get(j).getStatus());
            }
            floorstatus.add(new FloorStatus(floor.get(i).getOffice().getName(), floor.get(i).getOffice().getNum(),status));
        }
        return floorstatus;
    }
}
