package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.OrderDetailEntity;
import com.swp391.webapp.Service.Order_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/order-details")
public class Order_DetailController {

    @Autowired
    private Order_DetailService order_DetailService;

    @GetMapping
    public ResponseEntity<List<OrderDetailEntity>> getAllServiceDetails() {
        List<OrderDetailEntity> serviceDetails = order_DetailService.getAllServiceDetails();
        return ResponseEntity.ok(serviceDetails);
    }

    @GetMapping("/{serviceDetailId}")
    public ResponseEntity<OrderDetailEntity> getServiceDetailById(@PathVariable int serviceDetailId) {
        OrderDetailEntity serviceDetail = order_DetailService.getServiceDetailById(serviceDetailId);
        return ResponseEntity.ok(serviceDetail);
    }

    @PostMapping
    public ResponseEntity<OrderDetailEntity> saveServiceDetail(@RequestBody OrderDetailEntity serviceDetail) {
        OrderDetailEntity savedServiceDetail = order_DetailService.saveServiceDetail(serviceDetail);
        return ResponseEntity.ok(savedServiceDetail);
    }

    @DeleteMapping("/{serviceDetailId}")
    public ResponseEntity<Void> deleteServiceDetail(@PathVariable int serviceDetailId) {
        order_DetailService.deleteServiceDetail(serviceDetailId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}
