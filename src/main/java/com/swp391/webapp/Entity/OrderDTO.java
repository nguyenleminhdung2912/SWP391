package com.swp391.webapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_ID")
    private Integer orderID;

    @ManyToOne
    @JoinColumn(name = "account_ID", nullable = false)
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "package_ID", nullable = false)
    private PackageEntity packageEntity;

    @ManyToOne
    @JoinColumn(name = "schedule_ID", nullable = false)
    private ScheduleDTO scheduleDTO;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    // Constructors, getters, setters, etc.
}

