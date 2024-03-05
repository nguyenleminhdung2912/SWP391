package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.OrderEntity;
import com.swp391.webapp.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderEntity>> getAllOrders() {
        List<OrderEntity> Orders = orderService.getAllOrders();
        return ResponseEntity.ok(Orders);
    }

    @GetMapping("/{OrderId}")
    public ResponseEntity<OrderEntity> getOrderById(@PathVariable int OrderId) {
        OrderEntity Order = orderService.getOrderById(OrderId);
        return ResponseEntity.ok(Order);
    }

    @PostMapping
    public ResponseEntity<OrderEntity> saveOrder(@RequestBody OrderEntity Order) {
        OrderEntity savedOrder = orderService.saveOrder(Order);
        return ResponseEntity.ok(savedOrder);
    }

    @DeleteMapping("/{OrderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int OrderId) {
        orderService.deleteOrder(OrderId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}
