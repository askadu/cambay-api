package com.rozainfotech.cambayapi.controller;

import com.rozainfotech.cambayapi.converter.UserConverter;
import com.rozainfotech.cambayapi.entities.Organization;
import com.rozainfotech.cambayapi.entities.User;
import com.rozainfotech.cambayapi.models.FailureModel;
import com.rozainfotech.cambayapi.models.UserModel;
import com.rozainfotech.cambayapi.repositories.OrganizationRepository;
import com.rozainfotech.cambayapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api")
public class UserController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private PasswordEncoder  passwordEncoder;
    private UserRepository userRepository;
    private OrganizationRepository organizationRepository;

    public UserController(PasswordEncoder passwordEncoder,
                          UserRepository userRepository,
                          OrganizationRepository organizationRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.organizationRepository = organizationRepository;
    }

    @PostMapping("/authenticate")
    public Object authenticate(@RequestBody UserModel userModel) {
        User user = userRepository.findByEmailAndPassword(userModel.getEmail(), passwordEncoder.encode(userModel.getPassword()));
        if(user == null) {
            return new FailureModel("auhentication failed");
        }
        Optional<Organization> organization = organizationRepository.findById(user.getOrganizationId());
        if(organization.isPresent()) {
            Organization org = organization.get();
        }
        return UserConverter.toModel(user);
    }
}
