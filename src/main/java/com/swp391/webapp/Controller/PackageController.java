package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.PackageDTO;
import com.swp391.webapp.Entity.ServiceDTO;
import com.swp391.webapp.Service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/packages")
public class PackageController {

    @Autowired
    private PackageService packageService;

    @GetMapping
    public ResponseEntity<List<PackageDTO>> getAllPackages() {
        List<PackageDTO> packages = packageService.getAllPackages();
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/{packageId}")
    public ResponseEntity<PackageDTO> getPackageById(@PathVariable int packageId) {
        PackageDTO aPackage = packageService.getPackageById(packageId);
        return ResponseEntity.ok(aPackage);
    }

    @PostMapping
    public ResponseEntity<PackageDTO> savePackage(@RequestBody PackageDTO aPackage) {
        PackageDTO savedPackage = packageService.savePackage(aPackage);
        return ResponseEntity.ok(savedPackage);
    }

    @DeleteMapping("/{packageId}")
    public ResponseEntity<Void> deletePackage(@PathVariable int packageId) {
        packageService.deletePackage(packageId);
        return ResponseEntity.noContent().build();
    }

//    // Additional endpoints
//    @GetMapping("/{packageId}/services")
//    public ResponseEntity<List<ServiceDTO>> getServicesByPackageId(@PathVariable int packageId) {
//        List<ServiceDTO> services = packageService.getServicesByPackageId(packageId);
//        return ResponseEntity.ok(services);
//    }
}