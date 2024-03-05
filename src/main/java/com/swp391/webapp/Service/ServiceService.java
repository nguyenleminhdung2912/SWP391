package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.PackageEntity;
import com.swp391.webapp.Entity.ServiceEntity;
import com.swp391.webapp.Entity.ServiceOfPackageEntity;
import com.swp391.webapp.Repository.PackageRepository;
import com.swp391.webapp.Repository.ServiceOfPackageRepository;
import com.swp391.webapp.Repository.ServiceRepository;
import com.swp391.webapp.dto.ServiceDTO;
import com.swp391.webapp.utils.AccountUtils;
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

    @Autowired
    AccountUtils accountUtils;

    // ServiceDTO methods for ServiceDTO entity

    public List<ServiceEntity> getAllServices() {
        return serviceRepository.findAll();
    }

    public List<ServiceEntity> getAllServicesByHost(long id) {
        return serviceRepository.findServicesByAccountAccountID(id);
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

    public ServiceOfPackageEntity saveService(ServiceDTO service, long packageId) {
        PackageEntity packageEntity = packageRepository.findPackageByPackageID(packageId);
        ServiceOfPackageEntity serviceOfPackage = new ServiceOfPackageEntity();
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setAccount(accountUtils.getCurrentAccount());
        serviceEntity.setPrice(service.getPrice());
        serviceEntity.setName(service.getName());
        serviceEntity.setPicture(service.getPicture());
        serviceEntity.setDescription(service.getDescription());
        serviceOfPackage.setService(serviceEntity);
        serviceOfPackage.setPackageEntity(packageEntity);
        serviceEntity = serviceRepository.save(serviceEntity);
        return serviceOfPackageRepository.save(serviceOfPackage);
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
