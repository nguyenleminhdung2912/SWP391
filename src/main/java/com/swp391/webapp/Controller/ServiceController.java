package com.swp391.webapp.Controller;

import com.swp391.webapp.Config.SecuredRestController;
import com.swp391.webapp.Entity.ServiceEntity;
import com.swp391.webapp.Entity.ServiceOfPackageEntity;
import com.swp391.webapp.Repository.ServiceOfPackageRepository;
import com.swp391.webapp.Service.ServiceService;
import com.swp391.webapp.dto.ServiceDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/services")
public class ServiceController implements SecuredRestController {

    @Autowired
    private ServiceService serviceService;
    @Autowired
    AccountUtils accountUtils;
    @Autowired
    ServiceOfPackageRepository serviceOfPackageRepository;

    @GetMapping()
    public ResponseEntity<List<ServiceEntity>> getAllServices() {
        List<ServiceEntity> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }
    @GetMapping("/available")
    public ResponseEntity<List<ServiceEntity>> getAllAvailableServices() {
        List<ServiceEntity> services = serviceService.getAllAvailableServices();
        return ResponseEntity.ok(services);
    }
    @GetMapping("/package/{packageId}")
    public ResponseEntity<List<ServiceEntity>> getServiceByPackageId(@PathVariable int packageId) {
        List<ServiceEntity> services = serviceService.getServiceByPackageId(packageId);
        return ResponseEntity.ok(services);
    }

    @GetMapping("{serviceId}")
    public ResponseEntity<Optional<ServiceEntity>> getServiceId(@PathVariable int serviceId) {
        Optional<ServiceEntity> services = serviceService.getServiceById(serviceId);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<ServiceEntity>> getAllServicesByHost(@PathVariable int hostId) {
        List<ServiceEntity> services = serviceService.getAllServicesByHost(hostId);
        return ResponseEntity.ok(services);
    }

    @PostMapping("/addServiceToPackage/{packageId}")
    public ServiceOfPackageEntity saveServiceToPackage(@RequestBody ServiceDTO serviceDTO, @PathVariable int packageId) {
        return serviceService.saveServiceToPackage(serviceDTO, packageId);
    }

    @PostMapping("/addService")
    public ServiceEntity saveService(@RequestBody ServiceDTO serviceDTO) {
        return serviceService.saveService(serviceDTO);
    }

    @PostMapping("/{serviceId}/{packageId}")
    public ServiceOfPackageEntity addExistServicetoPackage(@PathVariable int serviceId, @PathVariable int packageId) {
        return serviceService.addExistServicetoPackage(serviceId, packageId);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable int serviceId) {
        serviceService.deleteService(serviceId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{serviceId}/{packageId}")
    public ResponseEntity<Void> deleteServiceOutOfPackage(@PathVariable int serviceId, @PathVariable int packageId) {
        serviceService.deleteServiceOutOfPackage(serviceId, packageId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ServiceEntity updateEachFieldById(@PathVariable int id, Map<String, Objects> fields) {
        return serviceService.updateEachFieldById(id, fields);
    }

    @PutMapping("/{id}")
    public ServiceEntity updateServiceById(@PathVariable int id, @RequestBody ServiceDTO serviceDTO) {
        return serviceService.updateServiceByID(id, serviceDTO);
    }

}
