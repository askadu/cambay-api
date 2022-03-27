package com.rozainfotech.cambayapi.filter;

import com.rozainfotech.cambayapi.models.CambayUser;
import com.rozainfotech.cambayapi.service.JwtService;
import com.rozainfotech.cambayapi.serviceimpl.CambayUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

    private JwtService jwtService;
    private CambayUserDetailsService cambayUserDetailsService;

    public JwtRequestFilter(JwtService jwtService,
                            CambayUserDetailsService cambayUserDetailsService) {
        this.jwtService = jwtService;
        this.cambayUserDetailsService = cambayUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LOGGER.debug("Request uri: {}", request.getRequestURI());
        final String authorizationHeader = request.getHeader("Authorization");
        String userName = null;
        String jwtToken = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
            userName = jwtService.extractUserName(jwtToken);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            CambayUser cambayUser = cambayUserDetailsService.loadUserByUsername(userName);
            if (jwtService.validateToken(jwtToken, cambayUser)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        cambayUser, null, cambayUser.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            request.setAttribute("cambayUser", cambayUser);
        }
        filterChain.doFilter(request, response);
    }
}
