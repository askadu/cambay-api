package com.rozainfotech.cambayapi.serviceimpl;

import com.rozainfotech.cambayapi.StartApplication;
import com.rozainfotech.cambayapi.entities.Organization;
import com.rozainfotech.cambayapi.models.OrganizationModel;
import com.rozainfotech.cambayapi.service.OrganizationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class)
public class OrganizationServiceImplTest {

    @Autowired
    private OrganizationService organizationService;

    @Test
    public void shouldGetOrganization() throws Exception {
        OrganizationModel organization = organizationService.findById(2);
        System.out.println(organization.getId());
    }
}
