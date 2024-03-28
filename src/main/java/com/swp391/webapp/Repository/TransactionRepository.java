package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.TransactionEntity;
import com.swp391.webapp.Entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    // Custom queries or methods can be added here if needed
    List<TransactionEntity> findAllTransactionEntitiesByWallet(WalletEntity wallet);
}

