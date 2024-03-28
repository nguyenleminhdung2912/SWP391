package com.swp391.webapp.Entity;

import com.swp391.webapp.Entity.Enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_ID")
    private Integer transactionID;

    @ManyToOne
    @JoinColumn(name = "order_ID", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "wallet_ID", nullable = false)
    private WalletEntity wallet;

    @Column(name = "date")
    private Date createAt;

    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(nullable = true)
    private TransactionStatus transactionStatus;

    public TransactionEntity(OrderEntity order, WalletEntity wallet, Date createAt, BigDecimal totalPrice) {
        this.order = order;
        this.wallet = wallet;
        this.createAt = createAt;
        this.totalPrice = totalPrice;
        this.transactionStatus = null;
    }

    public TransactionEntity(OrderEntity order, WalletEntity wallet, Date createAt, BigDecimal totalPrice, TransactionStatus transactionStatus) {
        this.order = order;
        this.wallet = wallet;
        this.createAt = createAt;
        this.totalPrice = totalPrice;
        this.transactionStatus = transactionStatus;
    }

    // Constructors, getters, setters, etc.
}

