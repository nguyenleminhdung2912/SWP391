package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.OrderEntity;
import com.swp391.webapp.Entity.ScheduleBusyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleBusyRepository extends JpaRepository<ScheduleBusyEntity, Integer> {
    List<ScheduleBusyEntity> findAllScheduleBusyEntitiesByAccount(AccountEntity hostAccount);

}
