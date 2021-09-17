package com.demo.bean;


public class LoginResponseEntity {

    private String status;
    private String role;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LoginResponseEntity() {
    }

    public LoginResponseEntity(String status, String role) {
        super();
        this.status = status;
        this.role = role;
    }


}
