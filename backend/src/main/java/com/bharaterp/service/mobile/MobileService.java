package com.bharaterp.service.mobile;

import com.bharaterp.model.mobile.MobileDevice;
import com.bharaterp.model.mobile.PushNotification;
import com.bharaterp.repository.mobile.MobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MobileService {
    
    @Autowired
    private MobileRepository mobileRepository;
    
    // Device Registration
    public MobileDevice registerDevice(MobileDevice device) {
        device.setLastActiveAt(LocalDateTime.now());
        device.setStatus("ACTIVE");
        return mobileRepository.save(device);
    }
    
    public Optional<MobileDevice> getDeviceById(Long id) {
        return mobileRepository.findById(id);
    }
    
    public Optional<MobileDevice> getDeviceByDeviceId(String deviceId) {
        return mobileRepository.findByDeviceId(deviceId);
    }
    
    public MobileDevice updateDeviceStatus(Long id, String status) {
        MobileDevice device = mobileRepository.findById(id).orElseThrow();
        device.setStatus(status);
        return mobileRepository.save(device);
    }
    
    public MobileDevice updateLastActive(Long id) {
        MobileDevice device = mobileRepository.findById(id).orElseThrow();
        device.setLastActiveAt(LocalDateTime.now());
        return mobileRepository.save(device);
    }
    
    // Push Notifications
    public PushNotification createNotification(PushNotification notification) {
        notification.setIsSent(false);
        notification.setSentStatus("PENDING");
        return mobileRepository.save(notification);
    }
    
    public PushNotification markAsSent(Long id) {
        PushNotification notification = mobileRepository.findById(id).orElseThrow();
        notification.setIsSent(true);
        notification.setSentAt(LocalDateTime.now());
        notification.setSentStatus("SENT");
        return mobileRepository.save(notification);
    }
    
    public PushNotification markAsRead(Long id) {
        PushNotification notification = mobileRepository.findById(id).orElseThrow();
        notification.setIsRead(true);
        notification.setReadAt(LocalDateTime.now());
        return mobileRepository.save(notification);
    }
    
    public List<PushNotification> getUnreadNotifications(Long userId) {
        return mobileRepository.findByUserId(userId);
    }
}
