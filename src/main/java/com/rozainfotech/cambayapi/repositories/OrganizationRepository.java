package com.rozainfotech.cambayapi.repositories;

import com.rozainfotech.cambayapi.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
