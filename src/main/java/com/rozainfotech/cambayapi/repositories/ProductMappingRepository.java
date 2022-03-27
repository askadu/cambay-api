package com.rozainfotech.cambayapi.repositories;

import com.rozainfotech.cambayapi.entities.ProductMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductMappingRepository extends JpaRepository<ProductMapping, Integer> {
    public List<ProductMapping> findByOrganizationId(Integer orgId);
}
