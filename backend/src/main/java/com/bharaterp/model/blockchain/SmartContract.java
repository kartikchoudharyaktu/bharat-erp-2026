package com.bharaterp.model.blockchain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "smart_contracts")
public class SmartContract {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String contractName;
    
    @Column(nullable = false)
    private String contractType; // PURCHASE, SALES, EMPLOYMENT, ND, SERVICE, SUPPLY
    
    @Column(nullable = false)
    private String contractAddress; // Blockchain address
    
    private String creator;
    private String counterparty;
    
    private String terms; // JSON
    private String conditions; // JSON
    
    private String executionTrigger; // TIME, EVENT, CONDITION
    
    private String executionSchedule; // CRON
    
    private String status = "DEPLOYED"; // DRAFT, DEPLOYED, EXECUTING, COMPLETED, TERMINATED
    
    private String verificationHash;
    private String verifiedBy;
    private LocalDateTime verificationDate;
    
    private String auditTrail; // JSON
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getContractName() { return contractName; }
    public void setContractName(String contractName) { this.contractName = contractName; }
    
    public String getContractType() { return contractType; }
    public void setContractType(String contractType) { this.contractType = contractType; }
    
    public String getContractAddress() { return contractAddress; }
    public void setContractAddress(String contractAddress) { this.contractAddress = contractAddress; }
    
    public String getCreator() { return creator; }
    public void setCreator(String creator) { this.creator = creator; }
    
    public String getCounterparty() { return counterparty; }
    public void setCounterparty(String counterparty) { this.counterparty = counterparty; }
    
    public String getTerms() { return terms; }
    public void setTerms(String terms) { this.terms = terms; }
    
    public String getConditions() { return conditions; }
    public void setConditions(String conditions) { this.conditions = conditions; }
    
    public String getExecutionTrigger() { return executionTrigger; }
    public void setExecutionTrigger(String executionTrigger) { this.executionTrigger = executionTrigger; }
    
    public String getExecutionSchedule() { return executionSchedule; }
    public void setExecutionSchedule(String executionSchedule) { this.executionSchedule = executionSchedule; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getVerificationHash() { return verificationHash; }
    public void setVerificationHash(String verificationHash) { this.verificationHash = verificationHash; }
    
    public String getVerifiedBy() { return verifiedBy; }
    public void setVerifiedBy(String verifiedBy) { this.verifiedBy = verifiedBy; }
    
    public LocalDateTime getVerificationDate() { return verificationDate; }
    public void setVerificationDate(LocalDateTime verificationDate) { this.verificationDate = verificationDate; }
    
    public String getAuditTrail() { return auditTrail; }
    public void setAuditTrail(String auditTrail) { this.auditTrail = auditTrail; }
    
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
        if (contractAddress == null || contractAddress.isEmpty()) {
            contractAddress = "0x" + Long.toHexString(System.currentTimeMillis());
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
