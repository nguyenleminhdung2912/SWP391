package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.AccountEntity;
import com.swp391.webapp.Entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Integer> {
    // Additional custom queries if needed

    List<ServiceEntity> findServicesByAccountAccountID(int id);

    @Query("SELECT s FROM ServiceEntity s Join ServiceOfPackageEntity sop on s.serviceID = sop.service.id WHERE sop.packageEntity.id = ?1")
    List<ServiceEntity> findServicesByPackageId(int packageId);

    List<ServiceEntity> findServicesByIsDeleted(boolean a);
    List<ServiceEntity> findServicesByIsDeletedAndAccountAccountID(boolean a, int id);


}

