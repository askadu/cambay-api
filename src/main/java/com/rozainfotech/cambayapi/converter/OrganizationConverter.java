package com.rozainfotech.cambayapi.converter;

import com.rozainfotech.cambayapi.entities.Organization;
import com.rozainfotech.cambayapi.models.OrganizationModel;

public class OrganizationConverter {

    public static OrganizationModel toModel(Organization organization) {
        OrganizationModel organizationModel = new OrganizationModel();
        organizationModel.setId(organization.getId());
        organizationModel.setName(organization.getName());
        organizationModel.setEmail(organization.getEmail());
        organizationModel.setActive(organization.getActive());
        organizationModel.setWebsite(organization.getWebsite());
        organizationModel.setPhoneNo(organization.getPhoneNo());
        return organizationModel;
    }

    public static Organization toEntity(OrganizationModel organizationModel) {
        Organization organization = new Organization();
        organization.setId(organizationModel.getId());
        organization.setName(organizationModel.getName());
        organization.setEmail(organizationModel.getEmail());
        organization.setActive(organizationModel.getActive());
        organization.setWebsite(organizationModel.getWebsite());
        organization.setPhoneNo(organizationModel.getPhoneNo());
        return organization;
    }
}
