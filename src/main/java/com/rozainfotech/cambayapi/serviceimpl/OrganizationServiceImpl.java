package com.rozainfotech.cambayapi.serviceimpl;

import com.rozainfotech.cambayapi.models.OrganizationModel;
import com.rozainfotech.cambayapi.repositories.OrganizationRepository;
import com.rozainfotech.cambayapi.service.OrganizationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    @Transactional
    public OrganizationModel findById(Integer id) throws Exception {
        throw new UnsupportedOperationException("");
    }
}
