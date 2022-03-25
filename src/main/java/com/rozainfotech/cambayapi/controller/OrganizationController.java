package com.rozainfotech.cambayapi.controller;

import com.rozainfotech.cambayapi.entities.Organization;
import com.rozainfotech.cambayapi.repositories.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class OrganizationController {

    private OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationController(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @PostMapping(name = "/organization")
    public Organization save(@RequestBody Organization organization) {
        return organizationRepository.save(organization);
    }

    @PostMapping("/test")
    public Object getOrg(@RequestBody Organization organization) {
        System.out.println(organization.toString());
        Map<String, String> map = new HashMap<>();
        map.put("email", "contact@rozainfotech.com");
        return organizationRepository.save(organization);
    }

    @GetMapping("/test")
    public List<Organization> getAllOrgs() {
        return organizationRepository.findAll();
    }
}
