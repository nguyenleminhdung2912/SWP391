package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.HostDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<HostDTO, Integer> {
}
