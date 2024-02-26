package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountDTO, Integer> {
    Optional<AccountDTO> findByEmail(String email);
}
