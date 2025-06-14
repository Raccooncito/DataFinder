package com.webapi.datafinder.Material;

import com.webapi.datafinder.Material.DTO.MaterialGeneralResponseDto;
import com.webapi.datafinder.Material.DTO.MaterialRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/material")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public List<MaterialGeneralResponseDto> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @PostMapping
    public void createMaterial(@RequestBody MaterialRequestDto material) {
        materialService.createMaterial(material);
    }

    @DeleteMapping(path = "{materialCode}")
    public void deleteMaterial(@PathVariable("materialCode") String materialCode) {
        materialService.deleteMaterial(materialCode);
    }

    @PutMapping(path = "{materialCode}")
    public void updateMaterial(@PathVariable("materialCode") String materialCode,
                               @RequestBody MaterialRequestDto material) {
        materialService.updateMaterial(materialCode, material);
    }

}
