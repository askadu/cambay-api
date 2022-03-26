package com.rozainfotech.cambayapi.enumerator;

public enum RoleEnum {
    ADMIN(1), USER(2);

    public final Integer roleId;

    RoleEnum(Integer roleId) {
       this.roleId = roleId;
    }
}
