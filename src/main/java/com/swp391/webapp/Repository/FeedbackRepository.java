package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.FeedbackEntity;
import com.swp391.webapp.Entity.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackEntity, Integer> {
    // Additional custom queries if needed
    List<FeedbackEntity> findFeedbacksByHost(AccountEntity host);
    List<FeedbackEntity> findFeedbacksByaPackage(PackageEntity packageEntity);

}

