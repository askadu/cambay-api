package com.rozainfotech.cambayapi.service;

import com.rozainfotech.cambayapi.models.CambayUser;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.function.Function;

public interface JwtService {
    String extractUserName(String token);

    Date extractExpiration(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    String generateToken(CambayUser cambayUser);

    Boolean validateToken(String token, UserDetails userDetails);
}
