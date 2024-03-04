package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.PackageDTO;
import com.swp391.webapp.Repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    // Service methods for Package entity

    public List<PackageDTO> getAllPackages() {
        return packageRepository.findAll();
    }

    public Optional<PackageDTO> getPackageById(int packageId) {
        return packageRepository.findById(packageId);
    }

    public PackageDTO savePackage(PackageDTO aPackage) {
        return packageRepository.save(aPackage);
    }

    public void deletePackage(int packageId) {
        packageRepository.deleteById(packageId);
    }

    public PackageDTO updateEachFieldById(int id, Map<String, Objects> fields) {
            Optional<PackageDTO> existingUser = packageRepository.findById(id);
            if (existingUser.isPresent()) {
                fields.forEach((key, value) -> {
                    Field field = ReflectionUtils.findField(PackageDTO.class, key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field, existingUser.get(), value);
                });
                return packageRepository.save(existingUser.get());
            }
            return null;
    }
    public void test(){}


    // Additional service methods if needed
}
