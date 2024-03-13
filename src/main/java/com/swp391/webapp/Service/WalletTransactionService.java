package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.WalletEntity;
import com.swp391.webapp.Entity.WalletTransactionEntity;
import com.swp391.webapp.Repository.WalletTransactionRepository;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WalletTransactionService {
    @Autowired
    WalletTransactionRepository walletTransactionRepository;
    @Autowired
    AccountUtils accountUtils;
    @Autowired
    AccountService accountService;

    public WalletTransactionEntity getTransactionById(int transactionId){
        return walletTransactionRepository.findById(transactionId).get();
    }
    public WalletTransactionEntity saveTransaction(WalletTransactionEntity walletTransactionEntity) {
        return walletTransactionRepository.save(walletTransactionEntity);
    }

    public WalletTransactionEntity getTransactionByAccountId(int accountId) {
        AccountEntity account = accountService.getAccountById(accountId).get();
        return walletTransactionRepository.findWalletTransactionsByAccount(account);
    }

    public WalletTransactionEntity getTransactionByAccount() {
        AccountEntity account = accountUtils.getCurrentAccount();
        return walletTransactionRepository.findWalletTransactionsByAccount(account);
    }
}
