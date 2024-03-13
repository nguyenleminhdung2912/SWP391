package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.OrderDetailEntity;
import com.swp391.webapp.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Order_Detail_Repository extends JpaRepository<OrderDetailEntity, Integer> {
    // Additional custom queries if needed

    List<OrderDetailEntity> findAllOrderDetailEntitiesByOrder(OrderEntity order);
}

