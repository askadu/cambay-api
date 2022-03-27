package com.rozainfotech.cambayapi.controller;

import com.rozainfotech.cambayapi.models.AuthenticationRequest;
import com.rozainfotech.cambayapi.models.AuthenticationResponse;
import com.rozainfotech.cambayapi.models.CambayUser;
import com.rozainfotech.cambayapi.service.JwtService;
import com.rozainfotech.cambayapi.serviceimpl.CambayUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class UserController {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    private JwtService jwtService;
    private CambayUserDetailsService cambayUserDetailsService;
    private AuthenticationManager authenticationManager;

    public UserController(JwtService jwtService,
                          CambayUserDetailsService cambayUserDetailsService,
                          AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.cambayUserDetailsService = cambayUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect username or password", ex);
        }

        CambayUser cambayUser = cambayUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwtToken = jwtService.generateToken(cambayUser);
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }
}
