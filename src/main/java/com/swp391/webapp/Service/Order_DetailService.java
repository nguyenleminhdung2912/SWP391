package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.OrderDetailEntity;
import com.swp391.webapp.Entity.OrderEntity;
import com.swp391.webapp.Repository.OrderRepository;
import com.swp391.webapp.Repository.Order_Detail_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class Order_DetailService {

    @Autowired
    private Order_Detail_Repository service_DetailRepository;
    @Autowired
    private OrderRepository orderRepository;

    // ServiceDTO methods for Service_Detail entity

    public List<OrderDetailEntity> getAllServiceDetails() {
        return service_DetailRepository.findAll();
    }



    public List<OrderDetailEntity> getAllServiceDetailsByOrderId(int orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).get();
        return service_DetailRepository.findAllOrderDetailEntitiesByOrder(orderEntity);
    }

    public OrderDetailEntity getServiceDetailById(int serviceDetailId) {
        return service_DetailRepository.findById(serviceDetailId).orElse(null);
    }

    public OrderDetailEntity saveServiceDetail(OrderDetailEntity serviceDetail) {
        return service_DetailRepository.save(serviceDetail);
    }

    public void deleteServiceDetail(int serviceDetailId) {
        service_DetailRepository.deleteById(serviceDetailId);
    }

    // Additional service methods if needed
}
