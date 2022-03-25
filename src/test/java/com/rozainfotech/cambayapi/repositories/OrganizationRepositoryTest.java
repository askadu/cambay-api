package com.rozainfotech.cambayapi.repositories;

import com.rozainfotech.cambayapi.StartApplication;
import com.rozainfotech.cambayapi.entities.Organization;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class OrganizationRepositoryTest {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Test
    public void shouldSaveEntity() {
        Organization organization = organizationRepository.save(new Organization(null, "RozaInfotech", "contact@rozainfotech.com", "https://rozainfotech.com", "+91 900 900 9000", Boolean.FALSE));
        Assert.assertNotNull(organization.getId());
    }
}
