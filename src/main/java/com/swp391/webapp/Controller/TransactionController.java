package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.TransactionDTO;
import com.swp391.webapp.Service.TransactionService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<TransactionDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> getTransactionById(@PathVariable int transactionId) {
        return transactionService.getTransactionById(transactionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transaction) {
        transactionService.saveTransaction(transaction);
        return ResponseEntity.ok(transaction);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<TransactionDTO> updateTransaction(@PathVariable int transactionId, @RequestBody TransactionDTO updatedTransaction) {
        return transactionService.getTransactionById(transactionId)
                .map(existingTransaction -> {
                    existingTransaction.setOrder(updatedTransaction.getOrder());
                    existingTransaction.setWallet(updatedTransaction.getWallet());
                    existingTransaction.setVenue(updatedTransaction.getVenue());
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
