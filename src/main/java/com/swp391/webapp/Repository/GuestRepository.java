package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.GuestDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<GuestDTO, Integer> {
}
