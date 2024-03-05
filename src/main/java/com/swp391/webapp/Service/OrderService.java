package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.OrderDTO;
import com.swp391.webapp.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository OrderRepository;

    // ServiceDTO methods for Order entity

    public List<OrderDTO> getAllOrders() {
        return OrderRepository.findAll();
    }

    public OrderDTO getOrderById(int OrderId) {
        return OrderRepository.findById(OrderId).orElse(null);
    }

    public OrderDTO saveOrder(OrderDTO Order) {
        return OrderRepository.save(Order);
    }

    public void deleteOrder(int OrderId) {
        OrderRepository.deleteById(OrderId);
    }

    // Additional service methods if needed
}
