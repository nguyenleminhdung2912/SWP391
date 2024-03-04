package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.ServiceEntity;
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

    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }
    public List<ServiceEntity> getServicesByHostID(int id) {
        List<ServiceEntity> list = serviceRepository.findAll();
        List<ServiceEntity> listTemp = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getAccount().getAccountID().equals(id)) {
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    public ServiceEntity getServiceById(int serviceId) {
        return serviceRepository.findById(serviceId).orElse(null);
    }
    public ServiceEntity getServiceByPackageId(int packageId) {
        return serviceRepository.findById(packageId).orElse(null);
    }

    public ServiceEntity saveService(ServiceEntity service) {
        return serviceRepository.save(service);
    }

    public void deleteService(int serviceId) {
        serviceOfPackageRepository.deleteById(serviceId);
        serviceRepository.deleteById(serviceId);
    }

    public ServiceEntity updateEachFieldById(int id, Map<String, Objects> fields) {
        Optional<ServiceEntity> existingUser = serviceRepository.findById(id);
        if (existingUser.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(ServiceEntity.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingUser.get(), value);
            });
            return serviceRepository.save(existingUser.get());
        }
        return null;
    }

    public ServiceEntity updateServiceByID(int id, ServiceEntity serviceEntity) {
        ServiceEntity current = serviceRepository.findById(id).get();
        current.setAccount(serviceEntity.getAccount());
        current.setPrice(serviceEntity.getPrice());
        current.setName(serviceEntity.getName());
        current.setDescription(serviceEntity.getDescription());
        current.setPicture(serviceEntity.getPicture());
        return serviceRepository.save(current);
    }

    // Additional service methods if needed
}
