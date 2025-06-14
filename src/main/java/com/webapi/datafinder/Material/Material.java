package com.webapi.datafinder.Material;

import jakarta.persistence.*;

@Entity
@Table(name = "Materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String materialCode;

    @Column(unique = true, nullable = false)
    private String materialName;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MeasureUnit measureUnit;

    @Column(nullable = false)
    private Double minimumStock;

    public Material() {
    }

    public Material(String materialCode, String materialName, String description, MeasureUnit measureUnit, Double minimumStock) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.description = description;
        this.measureUnit = measureUnit;
        this.minimumStock = minimumStock;
    }

    public Long getId() {
        return id;
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
