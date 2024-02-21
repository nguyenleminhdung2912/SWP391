package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.PackageDTO;
import com.swp391.webapp.Repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    // Service methods for Package entity

    public List<PackageDTO> getAllPackages() {
        return packageRepository.findAll();
    }

    public PackageDTO getPackageById(int packageId) {
        return packageRepository.findById(packageId).orElse(null);
    }

    public PackageDTO savePackage(PackageDTO aPackage) {
        return packageRepository.save(aPackage);
    }

    public void deletePackage(int packageId) {
        packageRepository.deleteById(packageId);
    }

    // Additional service methods if needed
}
