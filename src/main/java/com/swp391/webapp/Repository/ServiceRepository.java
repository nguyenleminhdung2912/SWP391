package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {
    // Additional custom queries if needed
}

