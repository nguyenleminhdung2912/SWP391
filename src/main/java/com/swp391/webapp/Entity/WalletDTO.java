package com.swp391.webapp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wallet")
public class WalletDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_ID")
    private Integer walletID;

    @ManyToOne
    @JoinColumn(name = "account_ID", nullable = false)
    private AccountDTO account;

    @Column(name = "total_money")
    private BigDecimal totalMoney;

    // Constructors, getters, setters, etc.
}

