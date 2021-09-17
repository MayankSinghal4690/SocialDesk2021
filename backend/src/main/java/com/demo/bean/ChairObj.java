package com.demo.bean;

import java.util.Optional;

public class ChairObj {
    private String id;
    private int coordinate_X;
    private int coordinate_Y;
    private String rotation;
    private String status;
    private String teammembername;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCoordinate_X() {
        return coordinate_X;
    }

    public void setCoordinate_X(int coordinate_X) {
        this.coordinate_X = coordinate_X;
    }

    public int getCoordinate_Y() {
        return coordinate_Y;
    }

    public void setCoordinate_Y(int coordinate_Y) {
        this.coordinate_Y = coordinate_Y;
    }

    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTeammembername() {
        return teammembername;
    }

    public void setTeammembername(String teammembername) {
        this.teammembername = teammembername;
    }

    public ChairObj() {

    }

    public ChairObj(String id, int coordinate_X, int coordinate_Y, String rotation, String status,
                    String teammembername) {
        super();
        this.id = id;
        this.coordinate_X = coordinate_X;
        this.coordinate_Y = coordinate_Y;
        this.rotation = rotation;
        this.status = status;
        this.teammembername = teammembername;
    }

    @Override
    public String toString() {
        return "ChairObj [id=" + id + ", coordinate_X=" + coordinate_X + ", coordinate_Y=" + coordinate_Y + ", rotation="
                + rotation + ", status=" + status + ", teammembername=" + teammembername + "]";
    }
}
