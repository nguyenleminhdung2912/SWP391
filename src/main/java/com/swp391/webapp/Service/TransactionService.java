package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.TransactionDTO;
import com.swp391.webapp.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Optional<TransactionDTO> getTransactionById(int transactionId) {
        return transactionRepository.findById(transactionId);
    }

    public void saveTransaction(TransactionDTO transaction) {
        transactionRepository.save(transaction);
    }

    public void deleteTransaction(int transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
