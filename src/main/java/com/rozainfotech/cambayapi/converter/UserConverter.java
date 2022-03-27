package com.rozainfotech.cambayapi.converter;

import com.rozainfotech.cambayapi.entities.User;
import com.rozainfotech.cambayapi.models.UserModel;

public class UserConverter {

    public static UserModel toModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setName(user.getName());
        userModel.setEmail(user.getEmail());
        userModel.setRoleId(user.getRoleId());
        userModel.setOrganizationId(user.getOrganizationId());
        userModel.setPassword(user.getPassword());
        return userModel;
    }

    public static  User toEntity(UserModel userModel) {
        User user = new User();
        user.setId(userModel.getId());
        user.setName(userModel.getName());
        user.setEmail(userModel.getEmail());
        user.setRoleId(userModel.getRoleId());
        user.setOrganizationId(userModel.getOrganizationId());
        user.setPassword(userModel.getPassword());
        return user;
    }
}
