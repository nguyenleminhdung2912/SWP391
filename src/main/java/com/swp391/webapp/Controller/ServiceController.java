package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.FeedbackDTO;
import com.swp391.webapp.Entity.ServiceDTO;
import com.swp391.webapp.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        List<ServiceDTO> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceDTO> getServiceById(@PathVariable int serviceId) {
        ServiceDTO service = serviceService.getServiceById(serviceId);
        return ResponseEntity.ok(service);
    }

    @PostMapping
    public ResponseEntity<ServiceDTO> saveService(@RequestBody ServiceDTO service) {
        ServiceDTO savedService = serviceService.saveService(service);
        return ResponseEntity.ok(savedService);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable int serviceId) {
        serviceService.deleteService(serviceId);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints
    @GetMapping("/{serviceId}/feedbacks")
    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByServiceId(@PathVariable Long serviceId) {
        List<FeedbackDTO> feedbacks = serviceService.getFeedbacksByServiceId(serviceId);
        return ResponseEntity.ok(feedbacks);
    }
}
