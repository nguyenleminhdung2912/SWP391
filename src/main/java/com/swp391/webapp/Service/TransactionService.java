package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.TransactionEntity;
import com.swp391.webapp.Entity.WalletEntity;
import com.swp391.webapp.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletService walletService;

    public List<TransactionEntity> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<TransactionEntity> getTransactionById(int transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public void saveTransaction(TransactionEntity transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteTransaction(int transactionId) {
        transactionRepository.deleteById(transactionId);
    }

    public List<TransactionEntity> getAllTransactionsByWallet(int walletId) {
        WalletEntity wallet = walletService.getWalletById(walletId).get();
        return  transactionRepository.findAllTransactionEntitiesByWallet(wallet);
    }

    public List<TransactionEntity> getAllTransactionsByAccountId(int accountId) {
        WalletEntity wallet = walletService.getWalletByAccountId(accountId);
        return transactionRepository.findAllTransactionEntitiesByWallet(wallet);
    }
}
