package com.swp391.webapp.Service;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.PackageEntity;
import com.swp391.webapp.Repository.AccountRepository;
import com.swp391.webapp.Repository.PackageRepository;
import com.swp391.webapp.dto.PackageDTO;
import com.swp391.webapp.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class PackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountUtils accountUtils;

    // ServiceDTO methods for Package entity

    public List<PackageEntity> getAllPackages() {
        return packageRepository.findAll();
    }

    public List<PackageEntity> getAllPackagesByPartyHost() {
        List<PackageEntity> tempList = packageRepository.findPackagesByAccountAndIsDeletedFalse(accountUtils.getCurrentAccount());

//        for (int i = 0; i < tempList.size(); i++) {
//            if (tempList.get(i).isDeleted() == true) {
//                tempList.remove(tempList.get(i));
//            }
//        }
        return tempList;
    }

    public List<PackageEntity> getAllPackagesByPartyHost(int id) {
        AccountEntity account = accountRepository.findAccountByAccountID(id);
        return packageRepository.findPackagesByAccount(account);
    }

    public Optional<PackageEntity> getPackageById(int packageId) {
        return packageRepository.findById(packageId);
    }

    public PackageEntity savePackage(PackageDTO aPackageDTO) {
        AccountEntity account = accountUtils.getCurrentAccount();
        if (account.getStatus().equals("Inactivated")) {
            throw new RuntimeException("This account has not been activated. Please activate before adding packages");
        } else {
            PackageEntity packageEntity = new PackageEntity(accountUtils.getCurrentAccount(), aPackageDTO.getName(), aPackageDTO.getPrice(), aPackageDTO.getDescription(), aPackageDTO.getPicture());
            return packageRepository.save(packageEntity);
        }
    }

    public void deletePackage(int packageId) {
        PackageEntity p = packageRepository.findById(packageId).get();
        p.setDeleted(true);
        packageRepository.save(p);

    }

    public PackageEntity updateEachFieldById(int id, Map<String, Objects> fields) {
        Optional<PackageEntity> existingUser = packageRepository.findById(id);
        if (existingUser.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(PackageEntity.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingUser.get(), value);
            });
            return packageRepository.save(existingUser.get());
        }
        return null;
    }

    public void test() {
    }

    public PackageEntity updatePackage(int id, PackageDTO aPackageDTO) {
        PackageEntity current = packageRepository.findById(id).get();
        current.setName(aPackageDTO.getName());
        current.setPrice(aPackageDTO.getPrice());
        current.setPicture(aPackageDTO.getPicture());
        current.setDescription(aPackageDTO.getDescription());
        return packageRepository.save(current);
    }


    // Additional service methods if needed
}
