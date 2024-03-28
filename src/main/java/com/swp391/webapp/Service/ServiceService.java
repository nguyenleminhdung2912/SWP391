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
import java.math.BigDecimal;
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

    public List<ServiceEntity> getAllAvailableServices() {
        return serviceRepository.findServicesByIsDeleted(false);
    }

    public List<ServiceEntity> getAllServicesByHost(int id) {
        return serviceRepository.findServicesByIsDeletedAndAccountAccountID(false, id);
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

    public Optional<ServiceEntity> getServiceById(int serviceId) {
        return serviceRepository.findById(serviceId);
    }

    public List<ServiceEntity> getServiceByPackageId(int packageId) {
        return serviceRepository.findServicesByPackageId(packageId);
    }

    public ServiceOfPackageEntity saveServiceToPackage(ServiceDTO serviceDTO, int packageId) {
        PackageEntity packageEntity = packageRepository.findPackageByPackageID(packageId);
        ServiceOfPackageEntity serviceOfPackage = new ServiceOfPackageEntity();
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setAccount(accountUtils.getCurrentAccount());
        serviceEntity.setPrice(serviceDTO.getPrice());
        serviceEntity.setName(serviceDTO.getName());
        serviceEntity.setPicture(serviceDTO.getPicture());
        serviceEntity.setDescription(serviceDTO.getDescription());
        serviceOfPackage.setService(serviceEntity);
        serviceOfPackage.setPackageEntity(packageEntity);

        BigDecimal newTotalPrice = new BigDecimal(packageEntity.getPrice().longValue() + serviceEntity.getPrice().longValue());
        BigDecimal newDiscountedPrice = new BigDecimal(newTotalPrice.longValue() - (newTotalPrice.longValue() * packageEntity.getDiscountPercentage()) / 100);
        packageEntity.setPrice(newTotalPrice);
        packageEntity.setDiscountedPrice(newDiscountedPrice);


        serviceEntity = serviceRepository.save(serviceEntity);
        return serviceOfPackageRepository.save(serviceOfPackage);
    }

    public ServiceEntity saveService(ServiceDTO serviceDTO) {
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setAccount(accountUtils.getCurrentAccount());
        serviceEntity.setPrice(serviceDTO.getPrice());
        serviceEntity.setName(serviceDTO.getName());
        serviceEntity.setPicture(serviceDTO.getPicture());
        serviceEntity.setDescription(serviceDTO.getDescription());

        return serviceRepository.save(serviceEntity);
    }

    public ServiceOfPackageEntity addExistServicetoPackage(int serviceId, int packageId) {
        //Tim ra package
        PackageEntity packageEntity = packageRepository.findPackageByPackageID(packageId);

        //Tao rang buoc noi service -> package
        ServiceOfPackageEntity serviceOfPackage = new ServiceOfPackageEntity();

        //Tim service
        ServiceEntity serviceEntity = serviceRepository.findById(serviceId).get();

        serviceOfPackage.setService(serviceEntity);
        serviceOfPackage.setPackageEntity(packageEntity);

        BigDecimal newTotalPrice = new BigDecimal(packageEntity.getPrice().longValue() + serviceEntity.getPrice().longValue());
        BigDecimal newDiscountedPrice = new BigDecimal(newTotalPrice.longValue() - (newTotalPrice.longValue() * packageEntity.getDiscountPercentage()) / 100);
        packageEntity.setPrice(newTotalPrice);
        packageEntity.setDiscountedPrice(newDiscountedPrice);

        serviceEntity = serviceRepository.save(serviceEntity);
        return serviceOfPackageRepository.save(serviceOfPackage);
    }

    public ServiceEntity deleteService(int serviceId) {
        ServiceEntity serviceEntity = serviceRepository.findById(serviceId).get();

        //Xoa lien ket giua package va service
        //Tru tien trong package
        List<ServiceOfPackageEntity> serviceOfPackageEntityList = serviceOfPackageRepository.findServiceOfPackageEntitiesByService(serviceEntity);
        for (ServiceOfPackageEntity serviceOfPackage : serviceOfPackageEntityList) {
            PackageEntity packageEntity = packageRepository.findPackageByPackageID(serviceOfPackage.getPackageEntity().getPackageID());
            BigDecimal newDiscountedPrice = new BigDecimal(packageEntity.getDiscountedPrice().longValue() - (serviceEntity.getPrice().longValue() - (serviceEntity.getPrice().longValue() * packageEntity.getDiscountPercentage()) / 100));
            packageEntity.setPrice(new BigDecimal(packageEntity.getPrice().longValue() - serviceEntity.getPrice().longValue()));
            packageEntity.setDiscountedPrice(newDiscountedPrice);
            serviceOfPackageRepository.deleteById(serviceOfPackage.getServiceOfPackageID());
        }

        //Doi isDeleted = true
        serviceEntity.setDeleted(true);
        return serviceRepository.save(serviceEntity);
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

    public ServiceEntity updateServiceByID(int id, ServiceDTO serviceDTO) {
        ServiceEntity current = serviceRepository.findById(id).get();
        if (!(serviceDTO.getPrice() == null))
            current.setPrice(serviceDTO.getPrice());
        if (!(serviceDTO.getName() == null))
            current.setName(serviceDTO.getName());
        if (!(serviceDTO.getDescription() == null))
            current.setDescription(serviceDTO.getDescription());
        if (!serviceDTO.getPicture().isEmpty() || !serviceDTO.getPicture().isBlank())
            current.setPicture(serviceDTO.getPicture());
        return serviceRepository.save(current);
    }

    public void deleteServiceOutOfPackage(int serviceId, int packageId) {
        ServiceEntity serviceEntity = serviceRepository.findById(serviceId).get();
        PackageEntity packageEntity = packageRepository.findById(packageId).get();

        //Xoa lien ket giua package va service
        //Tru tien trong package
        List<ServiceOfPackageEntity> serviceOfPackageEntityList = serviceOfPackageRepository.findServiceOfPackageEntitiesByServiceAndPackageEntity(serviceEntity, packageEntity);
        for (ServiceOfPackageEntity serviceOfPackage : serviceOfPackageEntityList) {
            //Tinh lai gia discount cua package
            BigDecimal newDiscountedPrice = new BigDecimal(packageEntity.getDiscountedPrice().longValue() - (serviceEntity.getPrice().longValue() - (serviceEntity.getPrice().longValue() * packageEntity.getDiscountPercentage()) / 100));
            packageEntity.setPrice(new BigDecimal(packageEntity.getPrice().longValue() - serviceEntity.getPrice().longValue()));
            packageEntity.setDiscountedPrice(newDiscountedPrice);
            serviceOfPackageRepository.deleteById(serviceOfPackage.getServiceOfPackageID());
        }
    }

}
