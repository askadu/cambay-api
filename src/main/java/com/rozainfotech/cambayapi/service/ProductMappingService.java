package com.rozainfotech.cambayapi.service;

import com.rozainfotech.cambayapi.models.ProductMappingModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductMappingService {
    List<ProductMappingModel> getProductsByOrgId(Integer orgId);
    ProductMappingModel addProductMapping(ProductMappingModel productMappingModel);
    Boolean uploadProductMapping(MultipartFile multipartFile);
}
