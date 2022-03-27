package com.rozainfotech.cambayapi.models;

import com.rozainfotech.cambayapi.entities.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CambayUser extends User {
    private Integer userId;
    private Integer orgId;
    private Role role;

    public CambayUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Integer orgId, Integer userId) {
        super(username, password, authorities);
        this.orgId = orgId;
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
