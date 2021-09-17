package com.demo.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class UpcomingBooking {

    private int id;

    private String deskstatus;

    private Date enddate;

    private Date startdate;

    private String chairId;

    private String floorId;

    private String officeName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeskstatus() {
        return deskstatus;
    }

    public void setDeskstatus(String deskstatus) {
        this.deskstatus = deskstatus;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getChairId() {
        return chairId;
    }

    public void setChairId(String chairId) {
        this.chairId = chairId;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public UpcomingBooking() {
    }

    public UpcomingBooking(int id, String deskstatus, Date enddate, Date startdate, String chairId, String floorId, String officeName) {
        super();
        this.id = id;
        this.deskstatus = deskstatus;
        this.enddate = enddate;
        this.startdate = startdate;
        this.chairId = chairId;
        this.floorId = floorId;
        this.officeName = officeName;
    }


}
