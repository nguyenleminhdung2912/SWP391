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
@Table(name = "order_detail")
public class Order_Detail_DTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_ID")
    private Integer orderDetailID;

    @ManyToOne
    @JoinColumn(name = "order_ID", nullable = false)
    private OrderDTO order;

    @ManyToOne
    @JoinColumn(name = "service_ID", nullable = false)
    private ServiceDTO service;

    // Constructors, getters, setters, etc.
}

