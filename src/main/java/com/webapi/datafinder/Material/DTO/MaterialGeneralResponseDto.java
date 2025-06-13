package com.webapi.datafinder.Material.DTO;

import com.webapi.datafinder.Material.MeasureUnit;

public class MaterialGeneralResponseDto {

    private String materialCode;

    private String materialName;

    private MeasureUnit unit;

    public MaterialGeneralResponseDto() {
    }

    public MaterialGeneralResponseDto(String materialCode, String materialName, MeasureUnit unit) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.unit = unit;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public MeasureUnit getUnit() {
        return unit;
    }

    public void setUnit(MeasureUnit unit) {
        this.unit = unit;
    }
}
