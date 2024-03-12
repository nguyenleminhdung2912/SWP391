package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.TransactionEntity;
import com.swp391.webapp.Service.TransactionService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionEntity>> getAllTransactions() {
        List<TransactionEntity> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionEntity> getTransactionById(@PathVariable int transactionId) {
        return transactionService.getTransactionById(transactionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TransactionEntity> createTransaction(@RequestBody TransactionEntity transaction) {
        transactionService.saveTransaction(transaction);
        return ResponseEntity.ok(transaction);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionEntity> updateTransaction(@PathVariable int transactionId, @RequestBody TransactionEntity updatedTransaction) {
        return transactionService.getTransactionById(transactionId)
                .map(existingTransaction -> {
                    existingTransaction.setOrder(updatedTransaction.getOrder());
                    existingTransaction.setWallet(updatedTransaction.getWallet());
                    existingTransaction.setTotalPrice(updatedTransaction.getTotalPrice());
                    transactionService.saveTransaction(existingTransaction);
                    return ResponseEntity.ok(existingTransaction);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable int transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }
}
