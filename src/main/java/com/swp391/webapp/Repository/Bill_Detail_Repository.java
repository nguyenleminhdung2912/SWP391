package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.Bill_Detail_DTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Bill_Detail_Repository extends JpaRepository<Bill_Detail_DTO, Integer> {
    // Additional custom queries if needed
}

