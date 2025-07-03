package com.webapi.datafinder.Inventory;

import com.webapi.datafinder.Inventory.DTO.InventoryGeneralResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/Inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<InventoryGeneralResponseDto> GetAllInventory() {
        return inventoryService.GetAllInventory();
    }

    @PutMapping(path = "{MaterialCode}/{Amount}")
    public void UpdateStockOfMaterial(@PathVariable("MaterialCode") String materialCode,
                                      @PathVariable("Amount") int amount) {
        inventoryService.UpdateStockOfMaterial(materialCode, amount);
    }

}
