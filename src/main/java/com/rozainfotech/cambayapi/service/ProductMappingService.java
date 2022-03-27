package com.rozainfotech.cambayapi.service;

import com.rozainfotech.cambayapi.models.ProductMappingModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductMappingService {
    List<ProductMappingModel> getProductsByOrgId(Integer orgId);
    ProductMappingModel addProductMapping(ProductMappingModel productMappingModel);
}
