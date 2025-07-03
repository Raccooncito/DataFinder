package com.webapi.datafinder.Inventory;

import com.webapi.datafinder.Material.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByMaterial(Material material);

    Boolean existsByMaterial(Material material);

}
