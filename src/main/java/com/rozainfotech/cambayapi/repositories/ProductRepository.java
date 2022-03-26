package com.rozainfotech.cambayapi.repositories;

import com.rozainfotech.cambayapi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
