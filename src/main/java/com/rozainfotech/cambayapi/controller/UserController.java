package com.rozainfotech.cambayapi.controller;

import com.rozainfotech.cambayapi.entities.User;
import com.rozainfotech.cambayapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api")
public class UserController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PasswordEncoder  passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public Object authenticate(@RequestBody User user) {
        return null;
    }
}
