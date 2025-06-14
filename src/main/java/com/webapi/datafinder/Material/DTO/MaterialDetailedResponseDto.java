package com.webapi.datafinder.Material.DTO;

import com.webapi.datafinder.Material.MeasureUnit;

public class MaterialDetailedResponseDto {

    private String materialCode;

    private String materialName;

    private String description;

    private MeasureUnit unit;

    private Double minimumStock;

    public MaterialDetailedResponseDto() {
    }

    public MaterialDetailedResponseDto(String materialCode, String materialName, String description, MeasureUnit unit, Double minimumStock) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.description = description;
        this.unit = unit;
        this.minimumStock = minimumStock;
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

    public Double getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Double minimumStock) {
        this.minimumStock = minimumStock;
    }
}
