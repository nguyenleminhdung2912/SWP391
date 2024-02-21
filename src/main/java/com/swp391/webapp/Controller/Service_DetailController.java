package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.Service_Detail_DTO;
import com.swp391.webapp.Service.Service_DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-details")
public class Service_DetailController {

    @Autowired
    private Service_DetailService service_DetailService;

    @GetMapping
    public ResponseEntity<List<Service_Detail_DTO>> getAllServiceDetails() {
        List<Service_Detail_DTO> serviceDetails = service_DetailService.getAllServiceDetails();
        return ResponseEntity.ok(serviceDetails);
    }

    @GetMapping("/{serviceDetailId}")
    public ResponseEntity<Service_Detail_DTO> getServiceDetailById(@PathVariable int serviceDetailId) {
        Service_Detail_DTO serviceDetail = service_DetailService.getServiceDetailById(serviceDetailId);
        return ResponseEntity.ok(serviceDetail);
    }

    @PostMapping
    public ResponseEntity<Service_Detail_DTO> saveServiceDetail(@RequestBody Service_Detail_DTO serviceDetail) {
        Service_Detail_DTO savedServiceDetail = service_DetailService.saveServiceDetail(serviceDetail);
        return ResponseEntity.ok(savedServiceDetail);
    }

    @DeleteMapping("/{serviceDetailId}")
    public ResponseEntity<Void> deleteServiceDetail(@PathVariable int serviceDetailId) {
        service_DetailService.deleteServiceDetail(serviceDetailId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints if needed
}
