package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.PackageEntity;
import com.swp391.webapp.Service.PackageService;
import com.swp391.webapp.dto.Package;
import com.swp391.webapp.utils.AccountUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/packages")
@SecurityRequirement(name = "api")
public class PackageController {

    @Autowired
    private PackageService packageService;
    @Autowired
    private AccountUtils accountUtils;

    @GetMapping("/allPackages")
    public ResponseEntity<List<PackageEntity>> getAllPackages() {
        List<PackageEntity> packages = packageService.getAllPackages();
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/hostPackages")
    public ResponseEntity<List<PackageEntity>> getAllHostPackages() {
        List<PackageEntity> packages = packageService.getAllPackagesByPartyHost();
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/packages-of-host/{hostId}")
    public ResponseEntity<List<PackageEntity>> getAllHostPackagesByHost(@PathVariable long hostId) {
        List<PackageEntity> packages = packageService.getAllPackagesByPartyHost(hostId);
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/{packageId}")
    public ResponseEntity<Optional<PackageEntity>> getPackageById(@PathVariable int packageId) {
        Optional<PackageEntity> aPackage = packageService.getPackageById(packageId);
        return ResponseEntity.ok(aPackage);
    }

    @PostMapping
    public ResponseEntity<PackageEntity> savePackage(@RequestBody Package aPackage) {
        PackageEntity savedPackage = packageService.savePackage(aPackage);
        return ResponseEntity.ok(savedPackage);
    }

    @DeleteMapping("/{packageId}")
    public ResponseEntity<Void> deletePackage(@PathVariable int packageId) {
        packageService.deletePackage(packageId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public PackageEntity updateEachFieldById(@PathVariable int id, Map<String, Objects> fields) {
        return packageService.updateEachFieldById(id, fields);
    }

    @PutMapping("{packageId}")
    public ResponseEntity<PackageEntity> updatePackage(@PathVariable int packageId, @RequestBody Package aPackage) {
        PackageEntity updatePackage = packageService.updatePackage(packageId, aPackage);
        return ResponseEntity.ok(updatePackage);
    }

//    // Additional endpoints
//    @GetMapping("/{packageId}/services")
//    public ResponseEntity<List<ServiceDTO>> getServicesByPackageId(@PathVariable int packageId) {
//        List<ServiceDTO> services = packageService.getServicesByPackageId(packageId);
//        return ResponseEntity.ok(services);
//    }
}