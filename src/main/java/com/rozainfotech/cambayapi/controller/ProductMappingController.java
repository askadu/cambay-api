package com.rozainfotech.cambayapi.controller;

import com.rozainfotech.cambayapi.converter.ProductMappingConverter;
import com.rozainfotech.cambayapi.entities.Product;
import com.rozainfotech.cambayapi.entities.ProductMapping;
import com.rozainfotech.cambayapi.models.ProductMappingModel;
import com.rozainfotech.cambayapi.repositories.ProductMappingRepository;
import com.rozainfotech.cambayapi.repositories.ProductRepository;
import com.rozainfotech.cambayapi.service.ProductMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ProductMappingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductMappingController.class);

    private ProductMappingService productMappingService;

    public ProductMappingController(ProductMappingService productMappingService) {
        this.productMappingService = productMappingService;
    }

    @GetMapping("/product")
    public List<ProductMappingModel> getAllProducts() {
        return productMappingService.getProductsByOrgId(2);
    }

    @PostMapping("/product")
    public ProductMappingModel saveProductMapping(@RequestBody ProductMappingModel productMappingModel) {
        return productMappingService.addProductMapping(productMappingModel);
    }
}
