package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.TransactionDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionDTO, Integer> {
    // Custom queries or methods can be added here if needed
}

