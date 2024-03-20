package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.OrderEntity;
import com.swp391.webapp.Entity.Enum.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    // Additional custom queries if needed
    List<OrderEntity> findOrdersByStatus(OrderStatus orderStatus);

    @Query("SELECT o FROM OrderEntity o Join PackageEntity p on o.packageEntity.id = p.id WHERE p.account.Id = ?1")
    List<OrderEntity> findOrdersByHost(int hostId);

    @Query("SELECT o FROM OrderEntity o Join PackageEntity p on o.packageEntity.id = p.id WHERE (p.account.Id = ?1) AND (o.createAt BETWEEN ?2 and ?3) AND (status =?4)")
    List<OrderEntity> findOrdersByHostAndCreateAtBetweenAndStatus(int hostId, Date startMonth, Date endMonth, OrderStatus orderStatus);

    List<OrderEntity> findOrdersByAccount(AccountEntity account);
    List<OrderEntity> findOrdersByCreateAtBetweenAndStatus(Date startMonth, Date endMonth, OrderStatus orderStatus);

}

