package com.demo.bean;

import java.util.Date;

public class Detail {
    private Date startDate;
    private Date endDate;
    private String officeName;
    private int floorNo;

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", officeName='" + officeName + '\'' +
                ", floorNo=" + floorNo +
                '}';
    }

    public Detail() {

    }

    public Detail(Date startDate, Date endDate, String officeName, int floorNo) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.officeName = officeName;
        this.floorNo = floorNo;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public String getOfficeName() {
        return officeName;
    }

    public int getFloorNo() {
        return floorNo;
    }
}
