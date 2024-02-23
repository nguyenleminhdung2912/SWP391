package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.ServiceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceDTO, Integer> {
    // Additional custom queries if needed
}

