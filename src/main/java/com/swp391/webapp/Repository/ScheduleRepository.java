package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.ScheduleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleDTO, Integer> {
    // Additional custom queries if needed
}

