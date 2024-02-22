package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.ServiceOfPackageDTO;
import com.swp391.webapp.Service.ServiceOfPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/service-of-packages")
public class ServiceOfPackageController {

    @Autowired
    private ServiceOfPackageService serviceOfPackageService;

    @GetMapping
    public ResponseEntity<List<ServiceOfPackageDTO>> getAllServiceOfPackages() {
        List<ServiceOfPackageDTO> serviceOfPackages = serviceOfPackageService.getAllServiceOfPackages();
        return ResponseEntity.ok(serviceOfPackages);
    }

    @GetMapping("/{serviceOfPackageId}")
    public ResponseEntity<Optional<ServiceOfPackageDTO>> getServiceOfPackageById(@PathVariable int serviceOfPackageId) {
        Optional<ServiceOfPackageDTO> serviceOfPackage = serviceOfPackageService.getServiceOfPackageById(serviceOfPackageId);
        return ResponseEntity.ok(serviceOfPackage);
    }

    @PostMapping
    public ResponseEntity<ServiceOfPackageDTO> saveServiceOfPackage(@RequestBody ServiceOfPackageDTO serviceOfPackage) {
        ServiceOfPackageDTO savedServiceOfPackage = serviceOfPackageService.saveServiceOfPackage(serviceOfPackage);
        return ResponseEntity.ok(savedServiceOfPackage);
    }

    @DeleteMapping("/{serviceOfPackageId}")
    public ResponseEntity<Void> deleteServiceOfPackage(@PathVariable int serviceOfPackageId) {
        serviceOfPackageService.deleteServiceOfPackage(serviceOfPackageId);
        return ResponseEntity.noContent().build();
    }
}
