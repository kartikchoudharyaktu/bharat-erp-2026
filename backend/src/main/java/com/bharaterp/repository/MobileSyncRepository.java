package com.bharaterp.repository;

import com.bharaterp.model.MobileSync;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MobileSyncRepository extends JpaRepository<MobileSync, Long> {
    List<MobileSync> findByUserId(Long userId);
    List<MobileSync> findBySyncStatus(String syncStatus);
    List<MobileSync> findByDeviceId(String deviceId);
}
