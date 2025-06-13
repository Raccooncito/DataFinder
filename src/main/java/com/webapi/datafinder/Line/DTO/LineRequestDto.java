package com.webapi.datafinder.Line.DTO;

import com.webapi.datafinder.Line.LineStatus;

public class LineRequestDto {

    private String city;
    private String state;
    private String country;
    private String supervisorCode;
    private LineStatus status;

    public LineRequestDto() {
    }

    public LineRequestDto(String city, String state, String country, String supervisorCode, LineStatus status) {
        this.city = city;
        this.state = state;
        this.country = country;
        this.supervisorCode = supervisorCode;
        this.status = status;
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

    public LineStatus getStatus() {
        return status;
    }

    public void setStatus(LineStatus status) {
        this.status = status;
    }
}
