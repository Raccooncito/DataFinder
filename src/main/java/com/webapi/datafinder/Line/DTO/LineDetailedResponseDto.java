package com.webapi.datafinder.Line.DTO;

import com.webapi.datafinder.Line.LineStatus;
import com.webapi.datafinder.user.User;

import java.util.ArrayList;
import java.util.List;

public class LineDetailedResponseDto {

    private String lineCode;

    private String city;

    private String state;

    private String country;

    private String supervisorCode;

    private String supervisorName;

    private LineStatus status;

    private List<User> workers = new ArrayList<>();

    public LineDetailedResponseDto() {
    }

    public LineDetailedResponseDto(String lineCode, String city, String state, String country, String supervisorCode, String supervisorName, LineStatus status, List<User> workers) {
        this.lineCode = lineCode;
        this.city = city;
        this.state = state;
        this.country = country;
        this.supervisorCode = supervisorCode;
        this.supervisorName = supervisorName;
        this.status = status;
        this.workers = workers;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSupervisorCode() {
        return supervisorCode;
    }

    public void setSupervisorCode(String supervisorCode) {
        this.supervisorCode = supervisorCode;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public LineStatus getStatus() {
        return status;
    }

    public void setStatus(LineStatus status) {
        this.status = status;
    }

    public List<User> getWorkers() {
        return workers;
    }

    public void setWorkers(List<User> workers) {
        this.workers = workers;
    }
}
