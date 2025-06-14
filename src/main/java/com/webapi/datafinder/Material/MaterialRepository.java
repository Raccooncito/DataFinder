package com.webapi.datafinder.Material;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Long> {

    Optional<Material> findByMaterialCode(String materialCode);

    boolean existsByMaterialName(String materialName);

    Optional<Material> findTopByOrderByIdDesc();
}
