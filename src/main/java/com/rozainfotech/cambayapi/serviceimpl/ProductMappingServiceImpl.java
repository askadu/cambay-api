package com.rozainfotech.cambayapi.serviceimpl;

import com.rozainfotech.cambayapi.converter.ProductConverter;
import com.rozainfotech.cambayapi.converter.ProductMappingConverter;
import com.rozainfotech.cambayapi.entities.Product;
import com.rozainfotech.cambayapi.entities.ProductMapping;
import com.rozainfotech.cambayapi.models.ProductMappingModel;
import com.rozainfotech.cambayapi.models.ProductModel;
import com.rozainfotech.cambayapi.repositories.ProductMappingRepository;
import com.rozainfotech.cambayapi.repositories.ProductRepository;
import com.rozainfotech.cambayapi.service.ProductMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductMappingServiceImpl implements ProductMappingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductMappingServiceImpl.class);

    private ProductMappingRepository productMappingRepository;
    private ProductRepository productRepository;

    public ProductMappingServiceImpl(ProductMappingRepository productMappingRepository,
                                     ProductRepository productRepository) {
        this.productMappingRepository = productMappingRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductMappingModel> getProductsByOrgId(Integer orgId) {
        List<ProductMapping> productMappings = productMappingRepository.findByOrganizationId(orgId);
        List<ProductMappingModel> productMappingModels = new ArrayList<>();
        for (ProductMapping productMapping : productMappings) {
            Optional<Product> productOptional = productRepository.findById(productMapping.getProductId());
            if (productOptional.isPresent()) {
                productMappingModels.add(ProductMappingConverter.toModel(productMapping, productOptional.get()));
            }
        }

        return productMappingModels;
    }

    @Override
    public ProductMappingModel addProductMapping(ProductMappingModel productMappingModel) {
        ProductModel productModel = productMappingModel.getProductModel();
        Product product = ProductConverter.toEntity(productModel);
        productRepository.save(product);
        ProductMapping productMapping = ProductMappingConverter.toEntity(productMappingModel);
        productMapping.setProductId(product.getId());
        productMapping.setOrganizationId(productMappingModel.getOrganizationId());
        productMappingRepository.save(productMapping);

        ProductMappingModel response = ProductMappingConverter.toModel(productMapping, product);
        return response;
    }
}
