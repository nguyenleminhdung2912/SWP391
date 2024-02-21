package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.FeedbackDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackDTO, Integer> {
    // Additional custom queries if needed
    List<FeedbackDTO> getAllFeedbacksByGuestId(int id);
}

