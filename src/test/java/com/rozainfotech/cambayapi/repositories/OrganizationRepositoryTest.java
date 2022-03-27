package com.rozainfotech.cambayapi.repositories;

import com.rozainfotech.cambayapi.StartApplication;
import com.rozainfotech.cambayapi.entities.*;
import com.rozainfotech.cambayapi.enumerator.RoleEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class OrganizationRepositoryTest {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMappingRepository productMappingRepository;

    @Test
    public void shouldSaveEntity() {
        Organization organization = organizationRepository.save(new Organization(null, "RozaInfotech", "contact@rozainfotech.com", "https://rozainfotech.com", "+91 900 900 9000", Boolean.FALSE));
        Set<Organization> organizations = new HashSet<>();
        organizations.add(organization);
        Product product = new Product(null, "Concur");
        product = productRepository.save(product);
        Role role = new Role(RoleEnum.USER.roleId, RoleEnum.USER);
        User user = new User(null, organization.getName(), organization.getEmail(), passwordEncoder.encode("test"), role.getId(), organization.getId());
        userRepository.save(user);
        Assert.assertNotNull(organization.getId());
    }

    @Test
    public void shouldSaveProductMapping() {

        Optional<Organization> organization = organizationRepository.findById(3);
        Optional<Product> product = productRepository.findById(2);
        if(organization.isPresent() && product.isPresent()) {
            Organization org = organization.get();
            ProductMapping productMapping = new ProductMapping(null, 24, LocalDate.now(), Boolean.TRUE, product.get().getId(), org.getId());
            productMappingRepository.save(productMapping);
        }
    }
}
