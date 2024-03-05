package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.OrderEntity;
import com.swp391.webapp.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository OrderRepository;

    // ServiceDTO methods for Order entity

    public List<OrderEntity> getAllOrders() {
        return OrderRepository.findAll();
    }

    public OrderEntity getOrderById(int OrderId) {
        return OrderRepository.findById(OrderId).orElse(null);
    }

    public OrderEntity saveOrder(OrderEntity Order) {
        return OrderRepository.save(Order);
    }

    public void deleteOrder(int OrderId) {
        OrderRepository.deleteById(OrderId);
    }

    // Additional service methods if needed
}
