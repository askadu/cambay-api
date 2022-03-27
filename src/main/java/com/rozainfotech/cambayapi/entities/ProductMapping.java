package com.rozainfotech.cambayapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "organization_id"})
)
@Entity
@NoArgsConstructor
public class ProductMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer noOfUsers;
    private LocalDate expiryDate;

    @Column(name = "status")
    private Boolean active;

    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "organization_id")
    private Integer organizationId;

}
