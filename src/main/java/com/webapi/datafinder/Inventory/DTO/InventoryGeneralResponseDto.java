package com.webapi.datafinder.Inventory.DTO;

import com.webapi.datafinder.Material.MeasureUnit;

public class InventoryGeneralResponseDto {

    private String materialCode;
    private String materialName;
    private MeasureUnit unit;

    private int currentTotalStock;
    private int availableStock;

    private int reservedStock;
    private int expectedStock;

    public InventoryGeneralResponseDto() {
    }

    public InventoryGeneralResponseDto(String materialCode, String materialName, MeasureUnit unit, int currentTotalStock, int availableStock, int reservedStock, int expectedStock) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.unit = unit;
        this.currentTotalStock = currentTotalStock;
        this.availableStock = availableStock;
        this.reservedStock = reservedStock;
        this.expectedStock = expectedStock;
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

    public int getCurrentTotalStock() {
        return currentTotalStock;
    }

    public void setCurrentTotalStock(int currentTotalStock) {
        this.currentTotalStock = currentTotalStock;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public int getReservedStock() {
        return reservedStock;
    }

    public void setReservedStock(int reservedStock) {
        this.reservedStock = reservedStock;
    }

    public int getExpectedStock() {
        return expectedStock;
    }

    public void setExpectedStock(int expectedStock) {
        this.expectedStock = expectedStock;
    }
}
