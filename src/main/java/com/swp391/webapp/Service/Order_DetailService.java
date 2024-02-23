package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.Order_Detail_DTO;
import com.swp391.webapp.Repository.Order_Detail_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Order_DetailService {

    @Autowired
    private Order_Detail_Repository service_DetailRepository;

    // Service methods for Service_Detail entity

    public List<Order_Detail_DTO> getAllServiceDetails() {
        return service_DetailRepository.findAll();
    }

    public Order_Detail_DTO getServiceDetailById(int serviceDetailId) {
        return service_DetailRepository.findById(serviceDetailId).orElse(null);
    }

    public Order_Detail_DTO saveServiceDetail(Order_Detail_DTO serviceDetail) {
        return service_DetailRepository.save(serviceDetail);
    }

    public void deleteServiceDetail(int serviceDetailId) {
        service_DetailRepository.deleteById(serviceDetailId);
    }

    // Additional service methods if needed
}
