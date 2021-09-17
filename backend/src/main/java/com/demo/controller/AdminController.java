package com.demo.controller;

import com.demo.bean.Floor;
import com.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;
    @CrossOrigin(origins = "*")
    @PutMapping("/admin")
    public ResponseEntity<Object> blockFloor(@RequestBody FloorBooking floorBooking) {

        String floorId = floorBooking.getOfficeName().substring(0, 3) + floorBooking.getFloorNo();
        System.out.println(floorId);

        try {
            Floor bookedFloorDetails = adminService.blockFloor(floorId, floorBooking.getStatus());
            return new ResponseEntity<>(bookedFloorDetails, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("error" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private static class FloorBooking {

        private String officeName;
        private int floorNo;
        private String status;

        public String getOfficeName() {
            return officeName;
        }

        public void setOfficeName(String officeName) {
            this.officeName = officeName;
        }

        public int getFloorNo() {
            return floorNo;
        }

        public void setFloorNo(int floorNo) {
            this.floorNo = floorNo;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
