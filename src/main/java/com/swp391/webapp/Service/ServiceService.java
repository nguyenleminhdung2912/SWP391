package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountDTO;
import com.swp391.webapp.Entity.ServiceDTO;
import com.swp391.webapp.Repository.PackageRepository;
import com.swp391.webapp.Repository.ServiceOfPackageRepository;
import com.swp391.webapp.Repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private PackageRepository packageRepository;
    @Autowired
    private ServiceOfPackageRepository serviceOfPackageRepository;

    // Service methods for Service entity

    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll();
    }
    public List<ServiceDTO> getServicesByHostID(int id) {
        List<ServiceDTO> list = serviceRepository.findAll();
        List<ServiceDTO> listTemp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAccount().getAccountID().equals(id)) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
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

    public ServiceDTO updateEachFieldById(int id, Map<String, Objects> fields) {
        Optional<ServiceDTO> existingUser = serviceRepository.findById(id);
        if (existingUser.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(ServiceDTO.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingUser.get(), value);
            });
            return serviceRepository.save(existingUser.get());
        }
        return null;
    }

    public ServiceDTO updateServiceByID(int id, ServiceDTO serviceDTO) {
        ServiceDTO current = serviceRepository.findById(id).get();
        current.setAccount(serviceDTO.getAccount());
        current.setPrice(serviceDTO.getPrice());
        current.setName(serviceDTO.getName());
        current.setDescription(serviceDTO.getDescription());
        current.setPicture(serviceDTO.getPicture());
        return serviceRepository.save(current);
    }

    // Additional service methods if needed
}
