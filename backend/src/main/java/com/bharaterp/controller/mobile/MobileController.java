package com.bharaterp.controller.mobile;

import com.bharaterp.model.mobile.MobileDevice;
import com.bharaterp.model.mobile.PushNotification;
import com.bharaterp.service.mobile.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mobile")
@CrossOrigin(origins = "*")
public class MobileController {
    
    @Autowired
    private MobileService mobileService;
    
    @PostMapping("/register")
    public ResponseEntity<MobileDevice> registerDevice(@RequestBody MobileDevice device) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mobileService.registerDevice(device));
    }
    
    @GetMapping("/devices/{id}")
    public ResponseEntity<MobileDevice> getDeviceById(@PathVariable Long id) {
        return mobileService.getDeviceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/devices/{id}/status")
    public ResponseEntity<MobileDevice> updateDeviceStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(mobileService.updateDeviceStatus(id, status));
    }
    
    @PutMapping("/devices/{id}/active")
    public ResponseEntity<MobileDevice> updateLastActive(@PathVariable Long id) {
        return ResponseEntity.ok(mobileService.updateLastActive(id));
    }
    
    @PostMapping("/notifications")
    public ResponseEntity<PushNotification> sendNotification(@RequestBody PushNotification notification) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mobileService.createNotification(notification));
    }
    
    @GetMapping("/notifications/unread/{userId}")
    public ResponseEntity<List<PushNotification>> getUnreadNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(mobileService.getUnreadNotifications(userId));
    }
    
    @PutMapping("/notifications/{id}/read")
    public ResponseEntity<PushNotification> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(mobileService.markAsRead(id));
    }
}
