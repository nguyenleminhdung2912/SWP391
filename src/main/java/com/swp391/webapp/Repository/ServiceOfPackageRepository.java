package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.ServiceOfPackageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceOfPackageRepository extends JpaRepository<ServiceOfPackageDTO, Integer> {
    // Additional custom queries if needed
}

