package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderDTO, Integer> {
    // Additional custom queries if needed
}

