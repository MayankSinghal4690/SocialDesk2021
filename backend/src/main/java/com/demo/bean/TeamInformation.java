package com.demo.bean;

import java.util.Date;

public class TeamInformation{
    private String name;
    private String officeName;
    private String teamId;
    private int floorNo;
    private int bookingId;
    private Date startDate;
    private Date endDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
public TeamInformation(){

}
    public TeamInformation(String name, String officeName, String teamId, int floorNo, int bookingId, Date startDate, Date endDate) {
        this.name = name;
        this.officeName = officeName;
        this.teamId = teamId;
        this.floorNo = floorNo;
        this.bookingId = bookingId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
