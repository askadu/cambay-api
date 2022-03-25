package com.rozainfotech.cambayapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Organization extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
//    private List<User> users;
    private String name;
    private String email;
    private String website;
    private String phoneNo;
    private Boolean active;

}
