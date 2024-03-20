package com.swp391.webapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

@Table(name = "package")
public class PackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_ID")
    private Integer packageID;

    @ManyToOne
    @JoinColumn(name = "account_ID", nullable = false)
    private AccountEntity account;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;


    @Column
    private String description;

    @Column
    private String picture;

    @Column
    private boolean isDeleted = false;

    public PackageEntity(AccountEntity account, String name, BigDecimal price, String description, String picture) {
        this.account = account;
        this.name = name;
        this.price = price;
        this.description = description;
        this.picture = picture;
    }

    @OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    List<ServiceOfPackageEntity> serviceOfPackageEntities;
    // Constructors, getters, setters, etc.

    // Constructors, getters, setters, etc.
}

