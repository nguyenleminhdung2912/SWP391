package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.PackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Integer> {
    // Additional custom queries if needed

    List<PackageEntity> findPackagesByAccount(AccountEntity account);

    PackageEntity findPackageByPackageID(int id);

    List<PackageEntity> findPackagesByAccountAndIsDeletedFalse(AccountEntity account);

}

