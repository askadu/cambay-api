package com.rozainfotech.cambayapi.converter;

import com.rozainfotech.cambayapi.entities.Product;
import com.rozainfotech.cambayapi.entities.ProductMapping;
import com.rozainfotech.cambayapi.models.ProductMappingModel;

import java.util.ArrayList;
import java.util.List;

public class ProductMappingConverter {

    public static ProductMappingModel toModel(ProductMapping productMapping) {
        ProductMappingModel productMappingModel = new ProductMappingModel();
        productMappingModel.setId(productMapping.getId());
        productMappingModel.setProductId(productMapping.getProductId());
        productMappingModel.setActive(productMapping.getActive());
        productMappingModel.setExpiryDate(productMapping.getExpiryDate());
        productMappingModel.setNoOfUsers(productMapping.getNoOfUsers());
        productMappingModel.setOrganizationId(productMapping.getOrganizationId());
        return productMappingModel;
    }

    public static ProductMapping toEntity(ProductMappingModel productMappingModel) {
        ProductMapping productMapping = new ProductMapping();
        productMapping.setId(productMappingModel.getId());
        productMapping.setProductId(productMappingModel.getProductId());
        productMapping.setActive(productMappingModel.getActive());
        productMapping.setExpiryDate(productMappingModel.getExpiryDate());
        productMapping.setNoOfUsers(productMappingModel.getNoOfUsers());
        productMapping.setOrganizationId(productMappingModel.getOrganizationId());
        return productMapping;
    }

    public static ProductMappingModel toModel(ProductMapping productMapping, Product product) {
        ProductMappingModel productMappingModel = toModel(productMapping);
        productMappingModel.setProductModel(ProductConverter.toModel(product));
        return productMappingModel;
    }

    public static List<ProductMappingModel> toModel(List<ProductMapping> productMappings) {
        List<ProductMappingModel> productMappingModels = new ArrayList<>();
        for (ProductMapping productMapping : productMappings) {
            productMappingModels.add(toModel(productMapping));
        }
        return productMappingModels;
    }

    public static List<ProductMapping> toEntity(List<ProductMappingModel> productMappingModels) {
        List<ProductMapping> productMappings = new ArrayList<>();
        for (ProductMappingModel productMappingModel : productMappingModels) {
            productMappings.add(toEntity(productMappingModel));
        }
        return productMappings;
    }

}
