package com.swp391.webapp.Controller;

import com.swp391.webapp.Config.SecuredRestController;
import com.swp391.webapp.Entity.ServiceEntity;
import com.swp391.webapp.Service.ServiceService;
import com.swp391.webapp.dto.ServiceDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/services")
public class ServiceController implements SecuredRestController {

    @Autowired
    private ServiceService serviceService;
    @Autowired
    AccountUtils accountUtils;

    @GetMapping
    public ResponseEntity<List<ServiceEntity>> getAllServices() {
        List<ServiceEntity> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("{hostId}")
    public ResponseEntity<List<ServiceEntity>> getAllServicesByPartyHost(@PathVariable long hostId) {
        List<ServiceEntity> services = serviceService.getAllServicesByHost(hostId);
        return ResponseEntity.ok(services);
    }

//    @GetMapping("/{serviceId}")
//    public ResponseEntity<ServiceEntity> getServiceById(@PathVariable int serviceId) {
//        ServiceEntity service = serviceService.getServiceById(serviceId);
//        return ResponseEntity.ok(service);
//    }



    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable int serviceId) {
        serviceService.deleteService(serviceId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/host/{hostID}")
    public ResponseEntity<List<ServiceEntity>> getServicesByHostID(@PathVariable int hostID) {
        return ResponseEntity.ok(serviceService.getServicesByHostID(hostID));
    }

    @PatchMapping("/{id}")
    public ServiceEntity updateEachFieldById(@PathVariable int id, Map<String, Objects> fields) {
        return serviceService.updateEachFieldById(id, fields);
    }

    @PutMapping("/{id}")
    public ServiceEntity updateServiceById(@PathVariable int id, @RequestBody ServiceEntity serviceEntity) {
        return serviceService.updateServiceByID(id, serviceEntity);
    }

    // Additional endpoints
//    @GetMapping("/{serviceId}/feedbacks")
//    public ResponseEntity<List<FeedbackDTO>> getFeedbacksByServiceId(@PathVariable Long serviceId) {
//        List<FeedbackDTO> feedbacks = serviceService.getFeedbacksByServiceId(serviceId);
//        return ResponseEntity.ok(feedbacks);
//    }
}
