package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.PackageEntity;
import com.swp391.webapp.Entity.ServiceEntity;
import com.swp391.webapp.Entity.ServiceOfPackageEntity;
import com.swp391.webapp.Repository.ServiceOfPackageRepository;
import com.swp391.webapp.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceOfPackageService {

    @Autowired
    private ServiceOfPackageRepository serviceOfPackageRepository;
    @Autowired
    private ServiceRepository serviceRepository;

    // ServiceDTO methods for ServiceOfPackage entity

    public List<ServiceOfPackageEntity> getAllServiceOfPackages() {
        return serviceOfPackageRepository.findAll();
    }
//    public List<ServiceOfPackageDTO> getAllServiceOfPackagesByServiceId(int serviceId) {
//        //Optional<ServiceDTO> serviceDTO = serviceRepository.findById(serviceId);
//        return (List<ServiceOfPackageDTO>) serviceOfPackageRepository.findByService(serviceId);
//    }

    public Optional<ServiceOfPackageEntity> getServiceOfPackageById(int serviceOfPackageId) {
        return serviceOfPackageRepository.findById(serviceOfPackageId);
    }

    public ServiceOfPackageEntity saveServiceOfPackage(ServiceOfPackageEntity serviceOfPackage) {
        return serviceOfPackageRepository.save(serviceOfPackage);
    }

    public void deleteServiceOfPackage(int serviceOfPackageId) {
        serviceOfPackageRepository.deleteById(serviceOfPackageId);
    }

    public List<ServiceOfPackageEntity> getAllServiceOfPackageByService(ServiceEntity serviceEntity) {
        return serviceOfPackageRepository.findServiceOfPackageEntitiesByService(serviceEntity);
    }

    public List<ServiceOfPackageEntity> getAllServiceOfPackageByServiceAndPackage(ServiceEntity serviceEntity, PackageEntity packageEntity) {
        return serviceOfPackageRepository.findServiceOfPackageEntitiesByServiceAndPackageEntity(serviceEntity, packageEntity);
    }

    // Additional service methods if needed
}
