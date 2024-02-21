package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.FeedbackDTO;
import com.swp391.webapp.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    // Service methods for Feedback entity

    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public FeedbackDTO getFeedbackById(int feedbackId) {
        return feedbackRepository.findById(feedbackId).orElse(null);
    }

    public FeedbackDTO saveFeedback(FeedbackDTO feedback) {
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(int feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    // Additional service methods if needed
}
