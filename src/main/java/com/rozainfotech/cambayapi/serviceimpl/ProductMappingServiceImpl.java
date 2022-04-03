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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductMappingServiceImpl implements ProductMappingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductMappingServiceImpl.class);

    private static final String SHEET = "ProductMapping";
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

    @Override
    public Boolean uploadProductMapping(MultipartFile multipartFile) {
        Boolean response = Boolean.TRUE;
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<ProductMappingModel> productMappingModels = new ArrayList<>();
            int rowNumber = 0;
            while(rows.hasNext()) {
                Row currentRow = rows.next();
                if(rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                ProductMappingModel productMappingModel = new ProductMappingModel();
                int cellIndex = 0;

                while(cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch(cellIndex) {
                        case 0:
                            productMappingModel.setId(null);
                            break;
                        case 1:
                            productMappingModel.setNoOfUsers(Double.valueOf(currentCell.getNumericCellValue()).intValue());
                            break;
                        case 2:
                            productMappingModel.setExpiryDate(currentCell.getLocalDateTimeCellValue().toLocalDate());
                            break;
                        case 3:
                            productMappingModel.setActive(currentCell.getBooleanCellValue());
                            break;
                        case 4:
                            productMappingModel.setProductId(Double.valueOf(currentCell.getNumericCellValue()).intValue());
                            break;
                        case 5:
                            productMappingModel.setOrganizationId(Double.valueOf(currentCell.getNumericCellValue()).intValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                productMappingModels.add(productMappingModel);
            }
            workbook.close();
            productMappingRepository.saveAll(ProductMappingConverter.toEntity(productMappingModels));
        } catch(IOException ex) {
            response = false;
            LOGGER.error(ex.getMessage());
        }
        return response;
    }
}
