package com.rozainfotech.cambayapi.controller;

import com.rozainfotech.cambayapi.converter.OrganizationConverter;
import com.rozainfotech.cambayapi.entities.Organization;
import com.rozainfotech.cambayapi.entities.Role;
import com.rozainfotech.cambayapi.entities.User;
import com.rozainfotech.cambayapi.enumerator.RoleEnum;
import com.rozainfotech.cambayapi.models.FailureModel;
import com.rozainfotech.cambayapi.repositories.OrganizationRepository;
import com.rozainfotech.cambayapi.repositories.RoleRepository;
import com.rozainfotech.cambayapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class OrganizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    private OrganizationRepository organizationRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public OrganizationController(OrganizationRepository organizationRepository,
                                  UserRepository userRepository,
                                  RoleRepository roleRepository,
                                  PasswordEncoder passwordEncoder) {
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/organization")
    public Object saveOrganization(@RequestBody Organization orgRequest) {
        Organization organization =  organizationRepository.save(orgRequest);
        Role role = new Role(null, RoleEnum.ADMIN);
        role = roleRepository.save(role);
        User user = new User(null, orgRequest.getName(), orgRequest.getEmail(), passwordEncoder.encode("test"), role.getId(), organization.getId());
        user = userRepository.save(user);
        return OrganizationConverter.toModel(organization);
    }

    @GetMapping("/organization")
    public List<Organization> getAllOrgs() {
        return organizationRepository.findAll();
    }

    @GetMapping("/organization/{orgId}")
    public Object getOrg(@PathVariable("orgId") Integer orgId) {
        Optional<Organization> organization  = organizationRepository.findById(orgId);
        if(organization.isPresent()) {
            return OrganizationConverter.toModel(organization.get());
        } else {
            return new FailureModel("organization not found.");
        }
    }
}
