package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.FeedbackEntity;
import com.swp391.webapp.Entity.ServiceEntity;
import com.swp391.webapp.Repository.FeedbackRepository;
import com.swp391.webapp.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    // ServiceDTO methods for Feedback entity

    public List<FeedbackEntity> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public FeedbackEntity getFeedbackById(int feedbackId) {
        return feedbackRepository.findById(feedbackId).orElse(null);
    }

    public FeedbackEntity saveFeedback(FeedbackEntity feedback) {
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(int feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    public List<FeedbackEntity> getFeedbacksByService(int serviceId) {
        ServiceEntity serviceEntity = serviceRepository.findById(serviceId).get();
        return feedbackRepository.findFeedbacksByService(serviceEntity);
    }


    // Additional service methods if needed
}
