package com.swp391.webapp.Controller;

import com.swp391.webapp.Entity.PackageEntity;
import com.swp391.webapp.Service.PackageService;
import com.swp391.webapp.dto.PackageDTO;
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
    @GetMapping("/available")
    public ResponseEntity<List<PackageEntity>> getAllAvailablePackages() {
        List<PackageEntity> packages = packageService.getAllPackagesAvailable();
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/packages-of-host/no-variable")
    public ResponseEntity<List<PackageEntity>> getAllHostPackages() {
        List<PackageEntity> packages = packageService.getAllPackagesByPartyHost();
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/packages-of-host/{hostId}")
    public ResponseEntity<List<PackageEntity>> getAllHostPackagesByHost(@PathVariable int hostId) {
        List<PackageEntity> packages = packageService.getAllPackagesByPartyHost(hostId);
        return ResponseEntity.ok(packages);
    }

    @GetMapping("/{packageId}")
    public ResponseEntity<Optional<PackageEntity>> getPackageById(@PathVariable int packageId) {
        Optional<PackageEntity> aPackage = packageService.getPackageById(packageId);
        return ResponseEntity.ok(aPackage);
    }

    @PostMapping("/addPackage")
    public ResponseEntity<PackageEntity> savePackage(@RequestBody PackageDTO aPackageDTO) {
        PackageEntity savedPackage = packageService.savePackage(aPackageDTO);
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
    public PackageEntity updatePackage(@PathVariable int packageId, @RequestBody PackageDTO aPackageDTO) {
        PackageEntity updatePackage = packageService.updatePackage(packageId, aPackageDTO);
        return updatePackage;
    }

//    // Additional endpoints
//    @GetMapping("/{packageId}/services")
//    public ResponseEntity<List<ServiceDTO>> getServicesByPackageId(@PathVariable int packageId) {
//        List<ServiceDTO> services = packageService.getServicesByPackageId(packageId);
//        return ResponseEntity.ok(services);
//    }
}