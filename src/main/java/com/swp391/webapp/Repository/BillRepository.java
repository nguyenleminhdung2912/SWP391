package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.BillDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<BillDTO, Integer> {
    // Additional custom queries if needed
}

