package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.*;
import com.swp391.webapp.Repository.*;
import com.swp391.webapp.dto.FeedbackDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private Order_DetailService orderDetailService;
    @Autowired
    private AccountUtils accountUtils;
    @Autowired
    private AccountRepository accountRepository;

    // ServiceDTO methods for Feedback entity

    public List<FeedbackEntity> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public FeedbackEntity getFeedbackById(int feedbackId) {
        return feedbackRepository.findById(feedbackId).orElse(null);
    }

    public FeedbackDTO saveFeedback(int orderId, FeedbackDTO feedbackDTO) {
        LocalDate date = LocalDate.now();
        //Lay order
        OrderEntity orderEntity = orderRepository.findById(orderId).get();

        //Tao feedback
        FeedbackEntity feedback = new FeedbackEntity();
        feedback.setFeedbackDate(date);
        feedback.setGuest(orderEntity.getAccount());
        feedback.setHost(orderEntity.getPackageEntity().getAccount());
        feedback.setRating(feedbackDTO.getRating());
        feedback.setDescription(feedbackDTO.getDescription());
        feedbackRepository.save(feedback);

        return feedbackDTO;
    }

    public void deleteFeedback(int feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    public List<FeedbackEntity> getFeedbacksByHost(int hostId) {
        AccountEntity host = accountRepository.findById(hostId).get();
        return feedbackRepository.findFeedbacksByHost(host);
    }


    // Additional service methods if needed
}
