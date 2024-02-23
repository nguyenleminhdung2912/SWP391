package com.swp391.webapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "transaction")
public class TransactionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_ID")
    private Integer transactionID;

    @ManyToOne
    @JoinColumn(name = "order_ID", nullable = false)
    private OrderDTO order;

    @ManyToOne
    @JoinColumn(name = "wallet_ID", nullable = false)
    private WalletDTO wallet;

    @Column
    private String venue;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    // Constructors, getters, setters, etc.
}

