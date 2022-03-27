package com.rozainfotech.cambayapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Integer id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private Integer roleId;
    private Integer organizationId;
}
