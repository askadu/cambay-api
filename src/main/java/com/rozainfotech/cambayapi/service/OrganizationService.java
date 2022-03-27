package com.rozainfotech.cambayapi.service;

import com.rozainfotech.cambayapi.models.OrganizationModel;

public interface OrganizationService {
    OrganizationModel findById(Integer id) throws Exception;
}
