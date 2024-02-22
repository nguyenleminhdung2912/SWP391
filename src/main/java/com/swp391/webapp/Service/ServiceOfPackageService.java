package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.ServiceOfPackageDTO;
import com.swp391.webapp.Repository.ServiceOfPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceOfPackageService {

    @Autowired
    private ServiceOfPackageRepository serviceOfPackageRepository;

    // Service methods for ServiceOfPackage entity

    public List<ServiceOfPackageDTO> getAllServiceOfPackages() {
        return serviceOfPackageRepository.findAll();
    }

    public Optional<ServiceOfPackageDTO> getServiceOfPackageById(int serviceOfPackageId) {
        return serviceOfPackageRepository.findById(serviceOfPackageId);
    }

    public ServiceOfPackageDTO saveServiceOfPackage(ServiceOfPackageDTO serviceOfPackage) {
        return serviceOfPackageRepository.save(serviceOfPackage);
    }

    public void deleteServiceOfPackage(int serviceOfPackageId) {
        serviceOfPackageRepository.deleteById(serviceOfPackageId);
    }

    // Additional service methods if needed
}
