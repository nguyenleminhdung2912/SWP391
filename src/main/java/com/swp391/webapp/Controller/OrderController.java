package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.OrderDTO;
import com.swp391.webapp.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> Orders = orderService.getAllOrders();
        return ResponseEntity.ok(Orders);
    }

    @GetMapping("/{OrderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int OrderId) {
        OrderDTO Order = orderService.getOrderById(OrderId);
        return ResponseEntity.ok(Order);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTO Order) {
        OrderDTO savedOrder = orderService.saveOrder(Order);
        return ResponseEntity.ok(savedOrder);
    }

    @DeleteMapping("/{OrderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int OrderId) {
        orderService.deleteOrder(OrderId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}
