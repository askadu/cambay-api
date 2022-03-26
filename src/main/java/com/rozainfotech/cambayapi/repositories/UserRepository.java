package com.rozainfotech.cambayapi.repositories;

import com.rozainfotech.cambayapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
