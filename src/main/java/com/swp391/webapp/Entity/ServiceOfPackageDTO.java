package com.swp391.webapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "serviceofpackage")
public class ServiceOfPackageDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ServiceOfPackage_ID")
    private Integer serviceOfPackageId;

    @ManyToOne
    @JoinColumn(name = "package_ID", nullable = false)
    private PackageDTO packageDTO;

    @ManyToOne
    @JoinColumn(name = "service_ID", nullable = false)
    private ServiceDTO service;
}
