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
public class Service_Detail_DTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Service_Detail_ID")
    private Long serviceDetailId;

    @ManyToOne
    @JoinColumn(name = "Cart_ID", nullable = false)
    private CartDTO cart;

    @ManyToOne
    @JoinColumn(name = "service_ID", nullable = false)
    private ServiceDTO service;

    // Constructors, getters, and setters

    // Omitted for brevity
}
