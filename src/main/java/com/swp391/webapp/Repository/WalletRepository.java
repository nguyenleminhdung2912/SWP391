package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Integer> {
    WalletEntity findWalletByAccount(AccountEntity account);

    // Custom queries or methods can be added here if needed
}
