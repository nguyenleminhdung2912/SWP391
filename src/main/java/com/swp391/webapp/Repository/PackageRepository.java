package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.PackageDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<PackageDTO, Integer> {
    // Additional custom queries if needed
}

