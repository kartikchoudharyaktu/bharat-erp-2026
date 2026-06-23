package com.bharaterp.model.audit;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_trail")
public class AuditTrail {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String transactionId;
    
    @Column(nullable = false)
    private String module; // FINANCE, HR, INVENTORY, SALES, MANUFACTURING, etc.
    
    @Column(nullable = false)
    private String action; // CREATE, UPDATE, DELETE, APPROVE, REJECT, VIEW, EXPORT
    
    private String entityType; // CUSTOMER, PRODUCT, INVOICE, EMPLOYEE, etc.
    private Long entityId;
    
    @Column(nullable = false)
    private Long userId;
    private String username;
    private String userRole;
    
    @Column(length = 4000)
    private String oldValue; // JSON
    @Column(length = 4000)
    private String newValue; // JSON
    
    private String ipAddress;
    private String userAgent;
    private String location;
    
    private String status; // SUCCESS, FAILURE, PENDING
    
    private String reason;
    private String remarks;
    
    @Column(nullable = false)
    private String blockchainHash; // Immutable hash
    
    @Column(nullable = false)
    private String blockchainTimestamp;
    
    private Boolean isVerified = false;
    private String verificationHash;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    
    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }
    
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    
    public String getEntityType() { return entityType; }
    public void setEntityType(String entityType) { this.entityType = entityType; }
    
    public Long getEntityId() { return entityId; }
    public void setEntityId(Long entityId) { this.entityId = entityId; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }
    
    public String getOldValue() { return oldValue; }
    public void setOldValue(String oldValue) { this.oldValue = oldValue; }
    
    public String getNewValue() { return newValue; }
    public void setNewValue(String newValue) { this.newValue = newValue; }
    
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    
    public String getUserAgent() { return userAgent; }
    public void setUserAgent(String userAgent) { this.userAgent = userAgent; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public String getBlockchainHash() { return blockchainHash; }
    public void setBlockchainHash(String blockchainHash) { this.blockchainHash = blockchainHash; }
    
    public String getBlockchainTimestamp() { return blockchainTimestamp; }
    public void setBlockchainTimestamp(String blockchainTimestamp) { this.blockchainTimestamp = blockchainTimestamp; }
    
    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
    
    public String getVerificationHash() { return verificationHash; }
    public void setVerificationHash(String verificationHash) { this.verificationHash = verificationHash; }
    
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
        if (transactionId == null || transactionId.isEmpty()) {
            transactionId = "AUDIT-" + System.currentTimeMillis();
        }
        // Generate blockchain hash
        if (blockchainHash == null || blockchainHash.isEmpty()) {
            blockchainHash = generateBlockchainHash();
            blockchainTimestamp = LocalDateTime.now().toString();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    private String generateBlockchainHash() {
        // Simulate blockchain hash generation
        return "0x" + Long.toHexString(System.currentTimeMillis()) + 
               Long.toHexString(System.nanoTime());
    }
}
