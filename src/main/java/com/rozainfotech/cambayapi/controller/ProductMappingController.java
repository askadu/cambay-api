package com.rozainfotech.cambayapi.controller;

import com.rozainfotech.cambayapi.models.CambayUser;
import com.rozainfotech.cambayapi.models.FailureModel;
import com.rozainfotech.cambayapi.models.ProductMappingModel;
import com.rozainfotech.cambayapi.service.ProductMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
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
    public List<ProductMappingModel> getAllProducts(@RequestAttribute CambayUser cambayUser) {
        LOGGER.debug("Organization id: {}", cambayUser.getOrgId());
        return productMappingService.getProductsByOrgId(cambayUser.getOrgId());
    }

    @PostMapping("/product")
    public ProductMappingModel saveProductMapping(@RequestBody ProductMappingModel productMappingModel,
                                                  @RequestAttribute CambayUser cambayUser) {
        productMappingModel.setOrganizationId(cambayUser.getOrgId());
        return productMappingService.addProductMapping(productMappingModel);
    }

    @PostMapping("/product/upload")
    public ResponseEntity<FailureModel> uploadProductMapping(@RequestParam("file") MultipartFile multipartFile) {
        if(productMappingService.uploadProductMapping(multipartFile)) {
            return ResponseEntity.ok(new FailureModel("Upload succeed."));
        }
        return ResponseEntity.ok(new FailureModel("Upload failed."));
    }
}
