package com.swp391.webapp.Repository;

import com.swp391.webapp.Entity.PackageEntity;
import com.swp391.webapp.Entity.ServiceEntity;
import com.swp391.webapp.Entity.ServiceOfPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceOfPackageRepository extends JpaRepository<ServiceOfPackageEntity, Integer> {
    // Additional custom queries if needed
//    @Query(value = "select * from serviceofpackage where service_ID = :service_ID")
//    ServiceOfPackageDTO findByService(@Param("service_ID") int service_ID);
    List<ServiceOfPackageEntity> findServiceOfPackageEntitiesByService(ServiceEntity serviceEntity);
    List<ServiceOfPackageEntity> findServiceOfPackageEntitiesByServiceAndPackageEntity(ServiceEntity serviceEntity, PackageEntity packageEntity);
}

