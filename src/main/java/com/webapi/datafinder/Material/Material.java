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

    @Column(nullable = false)
    private MeasureUnit unit;

    @Column(nullable = false)
    private Double minimunStock;

    public Material() {
    }

    public Material(String materialCode, String materialName, String description, MeasureUnit unit, Double minimunStock) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.description = description;
        this.unit = unit;
        this.minimunStock = minimunStock;
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
