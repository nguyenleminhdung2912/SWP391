package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.Order_Detail_Entity;
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
    public ResponseEntity<List<Order_Detail_Entity>> getAllServiceDetails() {
        List<Order_Detail_Entity> serviceDetails = order_DetailService.getAllServiceDetails();
        return ResponseEntity.ok(serviceDetails);
    }

    @GetMapping("/{serviceDetailId}")
    public ResponseEntity<Order_Detail_Entity> getServiceDetailById(@PathVariable int serviceDetailId) {
        Order_Detail_Entity serviceDetail = order_DetailService.getServiceDetailById(serviceDetailId);
        return ResponseEntity.ok(serviceDetail);
    }

    @PostMapping
    public ResponseEntity<Order_Detail_Entity> saveServiceDetail(@RequestBody Order_Detail_Entity serviceDetail) {
        Order_Detail_Entity savedServiceDetail = order_DetailService.saveServiceDetail(serviceDetail);
        return ResponseEntity.ok(savedServiceDetail);
    }

    @DeleteMapping("/{serviceDetailId}")
    public ResponseEntity<Void> deleteServiceDetail(@PathVariable int serviceDetailId) {
        order_DetailService.deleteServiceDetail(serviceDetailId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}
