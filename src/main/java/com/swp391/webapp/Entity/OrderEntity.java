package com.swp391.webapp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.swp391.webapp.Entity.Enum.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity {
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
    private Schedule schedule;

    @Column(nullable = false)
    private int quantity;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(nullable = true)
    private BigDecimal depositedMoney;

    @Column(nullable = true)
    private BigDecimal remainingMoney;

    @Column(nullable = true)
    private Date createAt;

    @Column(nullable = true)
    private OrderStatus status;

    @Column(name = "venue", nullable = true)
    private String venue;

    @Column(name = "phone", nullable = true)
    private String phone;

    @Column(name = "customer_name",nullable = true)
    private String customerName;

    @Column(nullable = true)
    private String customerEmail;

    @Column(nullable = true)
    private String notes;

    @Column(nullable = false)
    @JsonFormat(pattern="MM-dd-yyyy")
    private Date date;
    // Constructors, getters, setters, etc.
}

