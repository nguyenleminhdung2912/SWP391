package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    Optional<AccountEntity> findByEmail(String email);

    AccountEntity findAccountByAccountID(long id);

    List<AccountEntity> findAccountsByIsDeleted(int deleted);
    List<AccountEntity> findAccountsByRoleAndIsDeleted(String role, int deleted);

}
