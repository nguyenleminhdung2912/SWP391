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
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_ID")
    private Integer walletID;

    @ManyToOne
    @JoinColumn(name = "account_ID", nullable = false)
    private AccountEntity account;

    @Column(name = "total_money")
    private BigDecimal totalMoney;

    public WalletEntity(AccountEntity account, BigDecimal totalMoney) {
        this.account = account;
        this.totalMoney = totalMoney;
    }

    // Constructors, getters, setters, etc.
}

