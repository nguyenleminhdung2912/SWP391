package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountDTO, Integer> {
}
