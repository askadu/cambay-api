package com.rozainfotech.cambayapi.entities;

import com.rozainfotech.cambayapi.enumerator.RoleEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum roleEnum;
}
