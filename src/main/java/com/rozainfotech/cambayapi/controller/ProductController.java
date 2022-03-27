package com.rozainfotech.cambayapi.controller;

import com.rozainfotech.cambayapi.converter.ProductConverter;
import com.rozainfotech.cambayapi.entities.Product;
import com.rozainfotech.cambayapi.models.FailureModel;
import com.rozainfotech.cambayapi.models.ProductModel;
import com.rozainfotech.cambayapi.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/admin")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/product")
    public Object saveProduct(@RequestBody ProductModel productModel) {
        Product product = ProductConverter.toEntity(productModel);
        productRepository.save(product);
        return ProductConverter.toModel(product);
    }

    @GetMapping("/product")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Object getProduct(@PathVariable("id") Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()) {
            return ProductConverter.toModel(product.get());
        } else {
            return new FailureModel("product not found.");
        }
    }
}
