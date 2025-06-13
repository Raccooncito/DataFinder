package com.webapi.datafinder.Material.DTO;

import com.webapi.datafinder.Material.MeasureUnit;

public class MaterialDetailedResponseDto {

    private String materialCode;

    private String materialName;

    private String description;

    private MeasureUnit unit;

    private Double minimunStock;

    public MaterialDetailedResponseDto() {
    }

    public MaterialDetailedResponseDto(String materialCode, String materialName, String description, MeasureUnit unit, Double minimunStock) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.description = description;
        this.unit = unit;
        this.minimunStock = minimunStock;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MeasureUnit getUnit() {
        return unit;
    }

    public void setUnit(MeasureUnit unit) {
        this.unit = unit;
    }

    public Double getMinimunStock() {
        return minimunStock;
    }

    public void setMinimunStock(Double minimunStock) {
        this.minimunStock = minimunStock;
    }
}
