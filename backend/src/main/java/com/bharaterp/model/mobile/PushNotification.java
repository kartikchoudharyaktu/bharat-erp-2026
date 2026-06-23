package com.bharaterp.model.mobile;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "push_notifications")
public class PushNotification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String notificationType; // SYSTEM, ALERT, REMINDER, PROMOTION
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false, length = 500)
    private String message;
    
    private String imageUrl;
    private String deepLink;
    
    @Column(nullable = false)
    private Long userId;
    
    private String deviceId;
    
    private Boolean isRead = false;
    private LocalDateTime readAt;
    
    private Boolean isSent = false;
    private LocalDateTime sentAt;
    private String sentStatus; // PENDING, SENT, FAILED
    
    private String action;
    private String actionData;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNotificationType() { return notificationType; }
    public void setNotificationType(String notificationType) { this.notificationType = notificationType; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public String getDeepLink() { return deepLink; }
    public void setDeepLink(String deepLink) { this.deepLink = deepLink; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
    
    public Boolean getIsRead() { return isRead; }
    public void setIsRead(Boolean isRead) { this.isRead = isRead; }
    
    public LocalDateTime getReadAt() { return readAt; }
    public void setReadAt(LocalDateTime readAt) { this.readAt = readAt; }
    
    public Boolean getIsSent() { return isSent; }
    public void setIsSent(Boolean isSent) { this.isSent = isSent; }
    
    public LocalDateTime getSentAt() { return sentAt; }
    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
    
    public String getSentStatus() { return sentStatus; }
    public void setSentStatus(String sentStatus) { this.sentStatus = sentStatus; }
    
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    
    public String getActionData() { return actionData; }
    public void setActionData(String actionData) { this.actionData = actionData; }
    
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        isSent = false;
        isRead = false;
        sentStatus = "PENDING";
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
