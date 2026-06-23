package com.bharaterp.repository.mobile;

import com.bharaterp.model.mobile.MobileDevice;
import com.bharaterp.model.mobile.PushNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MobileRepository extends JpaRepository<MobileDevice, Long> {
    
    // MobileDevice Queries
    Optional<MobileDevice> findByDeviceId(String deviceId);
    List<MobileDevice> findByUserId(Long userId);
    List<MobileDevice> findByIsActiveTrue();
    List<MobileDevice> findByStatus(String status);
    
    // PushNotification Queries
    List<PushNotification> findByUserId(Long userId);
    List<PushNotification> findByIsReadFalse();
    List<PushNotification> findByIsSentFalse();
}
