package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.GuestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<GuestDTO, Integer> {
//    List<GuestDTO> findByAccountId(int accountId);
}
