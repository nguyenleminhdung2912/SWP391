package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.WalletDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletDTO, Integer> {
    // Custom queries or methods can be added here if needed
}
