package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {
    // Additional custom queries if needed

    List<ServiceEntity> findServicesByAccountAccountID(long id);
}

