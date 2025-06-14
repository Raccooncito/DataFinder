package com.webapi.datafinder.Material;

import com.webapi.datafinder.Material.DTO.MaterialGeneralResponseDto;
import com.webapi.datafinder.Material.DTO.MaterialRequestDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public List<MaterialGeneralResponseDto> getAllMaterials() {
        return materialRepository.findAll().stream()
                .map(material -> new MaterialGeneralResponseDto(
                        material.getMaterialCode(),
                        material.getMaterialName(),
                        material.getMeasureUnit()
                ))
                .toList();
    }

    public void createMaterial(MaterialRequestDto newMaterial) {
        if(materialRepository.existsByMaterialName(newMaterial.getMaterialName())) {
            throw new IllegalStateException(
                    "The name " + newMaterial.getMaterialName() + "is already in use"
            );
        }

        materialRepository.save(
                new Material(
                        createMaterialCode(newMaterial.getMaterialName(), newMaterial.getMeasureUnit()),
                        newMaterial.getMaterialName(),
                        newMaterial.getDescription(),
                        newMaterial.getMeasureUnit(),
                        newMaterial.getMinimumStock()
                )
        );
    }

    @Transactional
    public void deleteMaterial(String materialCode) {
        Material materialToDelete = materialRepository.findByMaterialCode(materialCode)
                .orElseThrow(() ->
                new IllegalStateException("The material with code " + materialCode + "does not exists")
        );
        materialRepository.delete(materialToDelete);
    }

    @Transactional
    public void updateMaterial(String materialCode, MaterialRequestDto newMaterialData) {
        Material materialToUpdate = materialRepository.findByMaterialCode(materialCode)
                .orElseThrow(() ->
                        new IllegalStateException("The material with code " + materialCode + "does not exists")
                );

        updateMaterialName(materialToUpdate, newMaterialData.getMaterialName());
        updateMaterialDescription(materialToUpdate, newMaterialData.getDescription());
        updateMaterialMeasureUnit(materialToUpdate, newMaterialData.getMeasureUnit());
        updateMaterialMinimumStock(materialToUpdate, newMaterialData.getMinimumStock());
        updateMaterialCode(materialToUpdate);
    }

    private void updateMaterialName(Material material, String materialName) {
        if (materialName == null || materialName.isEmpty()) return;
        if (Objects.equals(material.getMaterialName(), materialName)) return;

        material.setMaterialName(materialName);
    }

    private void updateMaterialDescription(Material material, String description) {
        if (description == null || description.isEmpty()) return;

        material.setDescription(description);
    }

    private void updateMaterialMeasureUnit(Material material, MeasureUnit measureUnit) {
        if (measureUnit == null) return;
        if (Objects.equals(material.getMeasureUnit(), measureUnit)) return;

        material.setMeasureUnit(measureUnit);
    }

    private void updateMaterialMinimumStock(Material material, Double minimumStock) {
        if (minimumStock == null) return;
        if (material.getMinimumStock().equals(minimumStock)) return;

        material.setMinimumStock(minimumStock);
    }

    private String createMaterialCode(String materialName, MeasureUnit measureUnit){
        Long lastId = materialRepository.
                findTopByOrderByIdDesc().map(Material::getId).orElse(0L);

        return
                materialName.substring(0, 2).toUpperCase() +
                        measureUnit.toString().substring(0, 2).toUpperCase() +
                        "-" +
                        String.format("%04d", lastId + 1);
    }

    private void updateMaterialCode(Material material) {
        if (material.getMaterialCode() == null || material.getMaterialCode().length() < 9) {
            throw new IllegalArgumentException("Invalid material code format: " + material.getMaterialCode());
        }
        String initials = material.getMaterialName().substring(0, 2).toUpperCase() +
                material.getMeasureUnit().toString().substring(0, 2).toUpperCase();

        String restOfCode = material.getMaterialCode().substring(4);

        material.setMaterialCode(initials + restOfCode);
    }


}
