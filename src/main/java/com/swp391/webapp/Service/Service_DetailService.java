package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.Service_Detail_DTO;
import com.swp391.webapp.Repository.Service_Detail_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service_DetailService {

    @Autowired
    private Service_Detail_Repository service_DetailRepository;

    // Service methods for Service_Detail entity

    public List<Service_Detail_DTO> getAllServiceDetails() {
        return service_DetailRepository.findAll();
    }

    public Service_Detail_DTO getServiceDetailById(int serviceDetailId) {
        return service_DetailRepository.findById(serviceDetailId).orElse(null);
    }

    public Service_Detail_DTO saveServiceDetail(Service_Detail_DTO serviceDetail) {
        return service_DetailRepository.save(serviceDetail);
    }

    public void deleteServiceDetail(int serviceDetailId) {
        service_DetailRepository.deleteById(serviceDetailId);
    }

    // Additional service methods if needed
}
