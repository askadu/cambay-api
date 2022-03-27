package com.rozainfotech.cambayapi.converter;

import com.rozainfotech.cambayapi.entities.Product;
import com.rozainfotech.cambayapi.models.ProductModel;

public class ProductConverter {

    public static ProductModel toModel(Product product) {
        ProductModel productModel = new ProductModel();
        productModel.setId(product.getId());
        productModel.setName(product.getName());
        return productModel;
    }

    public static Product toEntity(ProductModel productModel) {
        Product product = new Product();
        product.setId(productModel.getId());
        product.setName(productModel.getName());
        return product;
    }
}
