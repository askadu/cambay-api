package com.rozainfotech.cambayapi.enumerator;

import java.util.HashMap;
import java.util.Map;

public enum RoleEnum {
    ADMIN(1), USER(2);

    public final Integer roleId;

    RoleEnum(Integer roleId) {
        this.roleId = roleId;
    }

    private static final Map<Integer, RoleEnum> lookup = new HashMap<>();

    static {
        for (RoleEnum value : RoleEnum.values()) {
            lookup.put(value.roleId, value);
        }
    }

    public static RoleEnum get(Integer roleId) {
        return lookup.get(roleId);
    }
}
