package com.rozainfotech.cambayapi.serviceimpl;

import com.rozainfotech.cambayapi.entities.Role;
import com.rozainfotech.cambayapi.enumerator.RoleEnum;
import com.rozainfotech.cambayapi.models.CambayUser;
import com.rozainfotech.cambayapi.repositories.RoleRepository;
import com.rozainfotech.cambayapi.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class CambayUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public CambayUserDetailsService(UserRepository userRepository,
                                    RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public CambayUser loadUserByUsername(String username) throws UsernameNotFoundException {
        com.rozainfotech.cambayapi.entities.User user = userRepository.findByEmail(username);
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(RoleEnum.get(user.getRoleId()).name());
        return new CambayUser(user.getEmail(), user.getPassword(), new ArrayList<>(Arrays.asList(simpleGrantedAuthority)), user.getOrganizationId(), user.getId());
    }
}
