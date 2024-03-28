package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.ServiceOfPackageEntity;
import com.swp391.webapp.Service.ServiceOfPackageService;
import com.swp391.webapp.Service.ServiceService;
import com.swp391.webapp.dto.ServiceDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/service-of-packages")
@SecurityRequirement(name = "api")
public class ServiceOfPackageController {

    @Autowired
    private ServiceOfPackageService serviceOfPackageService;

    @Autowired
    ServiceService serviceService;

    @PostMapping("{packageId}")
    public ResponseEntity<ServiceOfPackageEntity> saveService(@RequestBody ServiceDTO serviceDTO, @PathVariable int packageId) {
        ServiceOfPackageEntity savedService = serviceService.saveServiceToPackage(serviceDTO, packageId);
        return ResponseEntity.ok(savedService);
    }
    @GetMapping
    public ResponseEntity<List<ServiceOfPackageEntity>> getAllServiceOfPackages() {
        List<ServiceOfPackageEntity> serviceOfPackages = serviceOfPackageService.getAllServiceOfPackages();
        return ResponseEntity.ok(serviceOfPackages);
    }

//    @GetMapping("/service/{serviceId}")
//    public ResponseEntity<List<ServiceOfPackageDTO>> getAllServiceOfPackagesByServiceID(@PathVariable int serviceId) {
//        List<ServiceOfPackageDTO> serviceOfPackages = serviceOfPackageService.getAllServiceOfPackagesByServiceId(serviceId);
//        return ResponseEntity.ok(serviceOfPackages);
//    }

    @GetMapping("/{serviceOfPackageId}")
    public ResponseEntity<Optional<ServiceOfPackageEntity>> getServiceOfPackageById(@PathVariable int serviceOfPackageId) {
        Optional<ServiceOfPackageEntity> serviceOfPackage = serviceOfPackageService.getServiceOfPackageById(serviceOfPackageId);
        return ResponseEntity.ok(serviceOfPackage);
    }

//    @PostMapping
//    public ResponseEntity<ServiceOfPackageEntity> saveServiceOfPackage(@RequestBody ServiceOfPackageEntity serviceOfPackage) {
//        ServiceOfPackageEntity savedServiceOfPackage = serviceOfPackageService.saveServiceOfPackage(serviceOfPackage);
//        return ResponseEntity.ok(savedServiceOfPackage);
//    }

    @DeleteMapping("/{serviceOfPackageId}")
    public ResponseEntity<Void> deleteServiceOfPackage(@PathVariable int serviceOfPackageId) {
        serviceOfPackageService.deleteServiceOfPackage(serviceOfPackageId);
        return ResponseEntity.noContent().build();
    }
}
