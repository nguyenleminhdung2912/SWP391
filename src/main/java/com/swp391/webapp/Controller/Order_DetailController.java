package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.Order_Detail_DTO;
import com.swp391.webapp.Service.Order_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class Order_DetailController {

    @Autowired
    private Order_DetailService order_DetailService;

    @GetMapping
    public ResponseEntity<List<Order_Detail_DTO>> getAllServiceDetails() {
        List<Order_Detail_DTO> serviceDetails = order_DetailService.getAllServiceDetails();
        return ResponseEntity.ok(serviceDetails);
    }

    @GetMapping("/{serviceDetailId}")
    public ResponseEntity<Order_Detail_DTO> getServiceDetailById(@PathVariable int serviceDetailId) {
        Order_Detail_DTO serviceDetail = order_DetailService.getServiceDetailById(serviceDetailId);
        return ResponseEntity.ok(serviceDetail);
    }

    @PostMapping
    public ResponseEntity<Order_Detail_DTO> saveServiceDetail(@RequestBody Order_Detail_DTO serviceDetail) {
        Order_Detail_DTO savedServiceDetail = order_DetailService.saveServiceDetail(serviceDetail);
        return ResponseEntity.ok(savedServiceDetail);
    }

    @DeleteMapping("/{serviceDetailId}")
    public ResponseEntity<Void> deleteServiceDetail(@PathVariable int serviceDetailId) {
        order_DetailService.deleteServiceDetail(serviceDetailId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}
