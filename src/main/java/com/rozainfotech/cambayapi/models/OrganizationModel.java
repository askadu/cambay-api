package com.rozainfotech.cambayapi.models;

import lombok.Data;

import java.util.Set;

@Data
public class OrganizationModel {

    private Integer id;
    private String name;
    private String email;
    private String website;
    private String phoneNo;
    private Boolean active;
}
