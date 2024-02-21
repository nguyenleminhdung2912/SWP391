package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.ServiceDTO;
import com.swp391.webapp.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    // Service methods for Service entity

    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll();
    }

    public ServiceDTO getServiceById(int serviceId) {
        return serviceRepository.findById(serviceId).orElse(null);
    }

    public ServiceDTO saveService(ServiceDTO service) {
        return serviceRepository.save(service);
    }

    public void deleteService(int serviceId) {
        serviceRepository.deleteById(serviceId);
    }

    // Additional service methods if needed
}
