package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.Service_Detail_DTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Service_Detail_Repository extends JpaRepository<Service_Detail_DTO, Integer> {
    // Additional custom queries if needed
}

