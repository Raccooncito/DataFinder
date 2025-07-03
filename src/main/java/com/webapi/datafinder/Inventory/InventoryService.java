package com.webapi.datafinder.Inventory;

import com.webapi.datafinder.Inventory.DTO.InventoryGeneralResponseDto;
import com.webapi.datafinder.Material.Material;
import com.webapi.datafinder.Material.MaterialRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {


    private  final InventoryRepository inventoryRepository;
    private final MaterialRepository materialRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, MaterialRepository materialRepository) {
        this.inventoryRepository = inventoryRepository;
        this.materialRepository = materialRepository;
    }

    @PostConstruct
    public void initInventory() {
        SyncInventoryWithMaterials();
    }

    public void SyncInventoryWithMaterials() {
        List<Material> materials = materialRepository.findAll();

        for(Material material : materials) {
            if(!inventoryRepository.existsByMaterial(material)) {
                Inventory inventory = new Inventory(material, 0);
                inventoryRepository.save(inventory);
            }
        }

    }

    public List<InventoryGeneralResponseDto> GetAllInventory() {
        return inventoryRepository.findAll().stream()
                .map(inventory -> new InventoryGeneralResponseDto(
                        inventory.getMaterial().getMaterialCode(),
                        inventory.getMaterial().getMaterialName(),
                        inventory.getMaterial().getMeasureUnit(),
                        inventory.getCurrentStock(),
                        inventory.getCurrentStock(), // replace with logic current - reserved
                        inventory.getCurrentStock(), // replace with calculated reserved
                        inventory.getCurrentStock() // replace with expected arrivals
                )).toList();
    }

    @Transactional
    public void UpdateStockOfMaterial(String materialCode, int amount) {
        Material material = materialRepository.findByMaterialCode(materialCode).orElseThrow(
                () -> new IllegalStateException(
                        "The material with the code " + materialCode + " does not exists"
                )
        );

        Inventory inventory = inventoryRepository.findByMaterial(material).orElseThrow(
                () -> new IllegalStateException(
                        "The material with the code " + materialCode + " does not exists on the inventory"
                )
        );

        inventory.setCurrentStock(inventory.getCurrentStock() + amount);
    }

}
