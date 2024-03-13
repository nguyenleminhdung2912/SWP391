package com.swp391.webapp.Entity;

import com.swp391.webapp.Entity.Enum.WalletTransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "wallettransaction")
public class WalletTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_ID")
    private Integer transactionID;

    @ManyToOne
    @JoinColumn(name = "account_ID", nullable = false)
    private AccountEntity account;

    @ManyToOne
    @JoinColumn(name = "wallet_ID", nullable = false)
    private WalletEntity wallet;

    @Column(name = "date")
    private LocalDateTime createAt;

    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @Column(name = "status")
    private WalletTransactionStatus walletTransactionStatus;

    public WalletTransactionEntity(AccountEntity account, WalletEntity wallet, LocalDateTime createAt, BigDecimal totalPrice) {
        this.account = account;
        this.wallet = wallet;
        this.createAt = createAt;
        this.totalPrice = totalPrice;
        this.walletTransactionStatus = WalletTransactionStatus.NOT_DONE;
    }

    // Constructors, getters, setters, etc.
}