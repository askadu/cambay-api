package com.rozainfotech.cambayapi.service;

import com.rozainfotech.cambayapi.entities.Organization;
import com.rozainfotech.cambayapi.models.OrganizationModel;

public interface OrganizationService {
    public abstract OrganizationModel findById(Integer id) throws Exception;
}
