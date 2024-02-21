package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.CartDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartDTO, Integer> {
    // Additional custom queries if needed
}

