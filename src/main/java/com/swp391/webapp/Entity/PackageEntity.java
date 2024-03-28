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

    @Column(nullable = true)
    private BigDecimal price;

    @Column(nullable = true)
    private BigDecimal discountedPrice;

    @Column(nullable = false)
    private float discountPercentage;

    @Column
    private Integer maximumSlot;

//    @Column
//    private BigDecimal moneyPerSlot;

    @Column
    private String description;

    @Column
    private String picture;

    @Column
    private boolean isDeleted = false;

    public PackageEntity(AccountEntity account, String name, Integer maximumSlot,  float discountPercentage, String description, String picture) {
        this.account = account;
        this.name = name;
        this.maximumSlot = maximumSlot;
        this.discountPercentage = discountPercentage;
        this.price = new BigDecimal(0);
        this.discountedPrice = new BigDecimal(0);
        this.description = description;
        this.picture = picture;
    }

    @OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    List<ServiceOfPackageEntity> serviceOfPackageEntities;
}

