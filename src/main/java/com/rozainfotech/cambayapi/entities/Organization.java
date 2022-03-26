package com.rozainfotech.cambayapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Organization extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String website;
    private String phoneNo;
    private Boolean active;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private Set<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private Set<ProductMapping> productMappings;
}
