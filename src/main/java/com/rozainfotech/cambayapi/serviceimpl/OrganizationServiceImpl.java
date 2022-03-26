package com.rozainfotech.cambayapi.serviceimpl;

import com.rozainfotech.cambayapi.entities.Organization;
import com.rozainfotech.cambayapi.entities.ProductMapping;
import com.rozainfotech.cambayapi.entities.User;
import com.rozainfotech.cambayapi.models.OrganizationModel;
import com.rozainfotech.cambayapi.models.ProductMappingModel;
import com.rozainfotech.cambayapi.repositories.OrganizationRepository;
import com.rozainfotech.cambayapi.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    @Transactional
    public OrganizationModel findById(Integer id) throws Exception {
        Optional<Organization> organization = organizationRepository.findById(id);
        if(organization.isPresent()) {
            Organization org = organization.get();
            Set<ProductMapping> productMappings = org.getProductMappings();
            Set<User> users = org.getUsers();
            OrganizationModel orgModel = new OrganizationModel();
            orgModel.setId(org.getId());
            orgModel.setName(org.getName());
            orgModel.setEmail(org.getEmail());
            Set<ProductMappingModel> productMappingModels = new HashSet<>();
            for (ProductMapping productMapping : productMappings) {
                productMappingModels.add(new ProductMappingModel(productMapping.getOrganization().getId(), null));
            }

            orgModel.setProductMappingModels(productMappingModels);
            return orgModel;
        } else {
            throw new Exception("organization id doesn't exist");
        }
    }
}
