package com.demo.bean;

import javax.persistence.*;

@Entity
@Table(name = "office")
public class Office {
    @Id
    @Column(name = "offid")
    private String id;

    @Column(name = "Offname")
    private String name;

    @Column(name = "numfloor")
    private int num;

    @ManyToOne
    @JoinColumn(name = "locid")
    private Location location; //Foreign key*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Location getLocid() {
        return location;
    }

    public void setLocid(Location locid) {
        this.location = locid;
    }

    public Office() {

    }

    public Office(String id, String name, int num, Location location) {
        super();
        this.id = id;
        this.name = name;
        this.num = num;
        this.location = location;
    }
}
