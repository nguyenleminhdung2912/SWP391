package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer> {
    // Additional custom queries if needed
}

