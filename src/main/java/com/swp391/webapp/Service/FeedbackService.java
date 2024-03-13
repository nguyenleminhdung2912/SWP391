package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.FeedbackEntity;
import com.swp391.webapp.Entity.OrderEntity;
import com.swp391.webapp.Entity.OrderDetailEntity;
import com.swp391.webapp.Entity.ServiceEntity;
import com.swp391.webapp.Repository.FeedbackRepository;
import com.swp391.webapp.Repository.OrderRepository;
import com.swp391.webapp.Repository.PackageRepository;
import com.swp391.webapp.Repository.ServiceRepository;
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
        //Lay danh sach order detail
        List<OrderDetailEntity> orderDetailEntityList = orderDetailService.getAllServiceDetailsByOrderId(orderEntity.getOrderID());
        //Lay danh sach service
        List<ServiceEntity> listService = new ArrayList<>();
        for (int i = 0; i < orderDetailEntityList.size(); i++) {
            ServiceEntity serviceEntity = serviceRepository.findById(orderDetailEntityList.get(i).getService().getServiceID()).get();
            listService.add(serviceEntity);
        }
        for (int i = 0; i < listService.size(); i++) {
            FeedbackEntity feedback = new FeedbackEntity();
            feedback.setFeedbackDate(date);
            feedback.setService(listService.get(i));
            feedback.setAccount(accountUtils.getCurrentAccount());
            feedback.setRating(feedbackDTO.getRating());
            feedback.setDescription(feedbackDTO.getDescription());
            feedbackRepository.save(feedback);
        }
        return feedbackDTO;
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
