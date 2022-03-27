package com.rozainfotech.cambayapi.service;

import com.rozainfotech.cambayapi.models.ProductMappingModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductMappingService {
    public List<ProductMappingModel> getProductsByOrgId(Integer orgId);
    public ProductMappingModel addProductMapping(ProductMappingModel productMappingModel);
}
