package com.swp391.webapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "serviceofpackage")
public class ServiceOfPackageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_of_package_id")
    private Integer serviceOfPackageID;

    @ManyToOne
    @JoinColumn(name = "package_ID", nullable = false)
    @JsonIgnore
    private PackageEntity packageEntity;

    @ManyToOne
    @JoinColumn(name = "service_ID", nullable = false)
    @JsonIgnore
    private ServiceEntity service;



    // Constructors, getters, setters, etc.
}

