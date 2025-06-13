package com.webapi.datafinder.Line.DTO;

public class LineWorkerRequestDto {
    String lineCode;
    String workerCode;

    public LineWorkerRequestDto() {
    }

    public LineWorkerRequestDto(String lineCode, String workerCode) {
        this.lineCode = lineCode;
        this.workerCode = workerCode;
    }

    public String getLineCode() {
        return lineCode;
    }

    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }

    public String getWorkerCode() {
        return workerCode;
    }

    public void setWorkerCode(String workerCode) {
        this.workerCode = workerCode;
    }
}
