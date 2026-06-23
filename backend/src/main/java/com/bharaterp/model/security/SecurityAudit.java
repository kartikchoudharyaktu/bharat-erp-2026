package com.bharaterp.model.security;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "security_audits")
public class SecurityAudit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String eventType; // LOGIN, LOGOUT, ACCESS_DENIED, PASSWORD_CHANGE, ROLE_CHANGE, SUSPICIOUS
    
    @Column(nullable = false)
    private Long userId;
    
    private String username;
    private String userRole;
    
    private String ipAddress;
    private String userAgent;
    private String location;
    
    private String action;
    private String resource;
    private String method;
    
    private String status; // SUCCESS, FAILURE, BLOCKED
    
    private String details;
    private String errorMessage;
    
    private Boolean isAlert = false;
    private String alertLevel; // INFO, WARNING, CRITICAL
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
    
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    
    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    
    public String getResource() { return resource; }
    public void setResource(String resource) { this.resource = resource; }
    
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
    
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    
    public Boolean getIsAlert() { return isAlert; }
    public void setIsAlert(Boolean isAlert) { this.isAlert = isAlert; }
    
    public String getAlertLevel() { return alertLevel; }
    public void setAlertLevel(String alertLevel) { this.alertLevel = alertLevel; }
    
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
