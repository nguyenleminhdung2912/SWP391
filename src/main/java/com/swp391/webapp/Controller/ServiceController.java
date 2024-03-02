package com.swp391.webapp.Controller;

import com.swp391.webapp.Config.SecuredRestController;
import com.swp391.webapp.Entity.AccountDTO;
import com.swp391.webapp.Entity.ServiceDTO;
import com.swp391.webapp.Service.ServiceService;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/services")
public class ServiceController implements SecuredRestController {

    @Autowired
    private ServiceService serviceService;
    @Autowired
    AccountUtils accountUtils;

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

    @GetMapping("/host")
    public ResponseEntity<List<ServiceDTO>> getServicesByHostID() {
        AccountDTO accountDTO = accountUtils.getCurrentAccount();
        return ResponseEntity.ok(serviceService.getServicesByHostID(accountDTO));
    }

    // Additional endpoints
//    @GetMapping("/{serviceId}/feedbacks")
//    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByServiceId(@PathVariable Long serviceId) {
//        List<FeedbackDTO> feedbacks = serviceService.getFeedbacksByServiceId(serviceId);
//        return ResponseEntity.ok(feedbacks);
//    }
}
