package com.webapi.datafinder.Material.DTO;

import com.webapi.datafinder.Material.MeasureUnit;

public class MaterialRequestDto {

    private Long id;

    private String materialName;

    private String description;

    private MeasureUnit measureUnit;

    private Double minimumStock;

    public MaterialRequestDto() {
    }

    public MaterialRequestDto(Long id, String materialName, String description, MeasureUnit measureUnit, Double minimumStock) {
        this.id = id;
        this.materialName = materialName;
        this.description = description;
        this.measureUnit = measureUnit;
        this.minimumStock = minimumStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(MeasureUnit measureUnit) {
        this.measureUnit = measureUnit;
    }

    public Double getMinimumStock() {
        return minimumStock;
    }

    public void setMinimumStock(Double minimumStock) {
        this.minimumStock = minimumStock;
    }
}
