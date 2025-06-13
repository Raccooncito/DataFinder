package com.webapi.datafinder.Line.DTO;

import com.webapi.datafinder.Line.LineStatus;

public class LineGeneralResponseDto {
    private String lineCode;
    private String country;
    private String supervisorCode;
    private String supervisorName;
    private LineStatus status;

    public LineGeneralResponseDto() {
    }

    public LineGeneralResponseDto(String lineCode, String country, String supervisorCode, String supervisorName, LineStatus status) {
        this.lineCode = lineCode;
        this.country = country;
        this.supervisorCode = supervisorCode;
        this.supervisorName = supervisorName;
        this.status = status;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
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
}
