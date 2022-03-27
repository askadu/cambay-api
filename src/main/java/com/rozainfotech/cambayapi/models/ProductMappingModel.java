package com.rozainfotech.cambayapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductMappingModel {
    private Integer id;
    private Integer noOfUsers;
    private LocalDate expiryDate;
    private Boolean active;
    @JsonIgnore
    private Integer productId;
    @JsonIgnore
    private Integer organizationId;
    @JsonProperty("product")
    private ProductModel productModel;
}
