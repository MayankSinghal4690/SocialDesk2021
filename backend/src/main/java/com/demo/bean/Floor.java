package com.demo.bean;

import javax.persistence.*;

@Entity
@Table(name = "floor")
public class Floor {
    @Id
    @Column(name = "floorid")
    private String id;

    @Column(name = "numdesks")
    private int num;

    @Column(name = "layrow")
    private int rows;

    @Column(name = "laycol")
    private int cols;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "offid")
    private Office office; //Foreign key*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public Floor() {

    }

    public Floor(String id, int num, int rows, int cols, String status, Office office) {
        super();
        this.id = id;
        this.num = num;
        this.rows = rows;
        this.cols = cols;
        this.status = status;
        this.office = office;
    }


}