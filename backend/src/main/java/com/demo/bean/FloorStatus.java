package com.demo.bean;

import java.util.List;

public class FloorStatus {
    private String officeName;
    private int numFloor;
    private List<String> floorStatus;
    
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}
	public int getNumFloor() {
		return numFloor;
	}
	public void setNumFloor(int numFloor) {
		this.numFloor = numFloor;
	}
	public List<String> getFloorStatus() {
		return floorStatus;
	}
	public void setFloorStatus(List<String> floorStatus) {
		this.floorStatus = floorStatus;
	}
	public FloorStatus() {
		
	}
	public FloorStatus(String officeName, int numFloor, List<String> floorStatus) {
		super();
		this.officeName = officeName;
		this.numFloor = numFloor;
		this.floorStatus = floorStatus;
	}
    
	
    

    
}
