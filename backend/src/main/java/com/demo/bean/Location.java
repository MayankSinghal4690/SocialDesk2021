package com.demo.bean;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locid")
    private int id;

    @Column(name = "locname")
    private String name;

    @Column(name = "numoffice")
    private int num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Location(int id, String name, int num) {
        super();
        this.id = id;
        this.name = name;
        this.num = num;
    }

    public Location() {

    }
}
