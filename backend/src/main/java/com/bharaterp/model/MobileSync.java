package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "mobile_sync")
@Data
public class MobileSync {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String deviceId;
    
    @Column(nullable = false)
    private String deviceName;
    
    private String deviceType; // ANDROID, IOS, WEB
    
    @Column(name = "user_id")
    private Long userId;
    
    @Column(nullable = false)
    private String syncType; // FULL, INCREMENTAL, MANUAL
    
    @Column(nullable = false)
    private String status; // PENDING, SYNCING, COMPLETED, FAILED
    
    private String message;
    
    @Column(columnDefinition = "TEXT")
    private String syncData;
    
    @Column(name = "last_sync_at")
    private LocalDateTime lastSyncAt;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}