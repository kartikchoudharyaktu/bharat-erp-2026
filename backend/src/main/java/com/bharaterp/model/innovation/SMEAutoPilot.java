package com.bharaterp.model.innovation;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sme_auto_pilot")
public class SMEAutoPilot {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long userId;
    
    private String businessName;
    private String businessType; // SHOP, RETAIL, MANUFACTURING, SERVICE, FREELANCE
    
    private String gstStatus; // REGISTERED, UNREGISTERED, COMPOSITE
    
    private String autoPilotLevel; // BASIC, STANDARD, PREMIUM
    
    private Boolean autoGSTFiling = false;
    private Boolean autoPayroll = false;
    private Boolean autoInvoice = false;
    private Boolean autoInventory = false;
    private Boolean autoTax = false;
    private Boolean autoCompliance = false;
    
    private String schedule; // CRON expression
    
    private String lastRunStatus;
    private LocalDateTime lastRunDate;
    
    private String configuration; // JSON
    
    private String status = "ACTIVE"; // ACTIVE, INACTIVE, PAUSED
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getBusinessName() { return businessName; }
    public void setBusinessName(String businessName) { this.businessName = businessName; }
    
    public String getBusinessType() { return businessType; }
    public void setBusinessType(String businessType) { this.businessType = businessType; }
    
    public String getGstStatus() { return gstStatus; }
    public void setGstStatus(String gstStatus) { this.gstStatus = gstStatus; }
    
    public String getAutoPilotLevel() { return autoPilotLevel; }
    public void setAutoPilotLevel(String autoPilotLevel) { this.autoPilotLevel = autoPilotLevel; }
    
    public Boolean getAutoGSTFiling() { return autoGSTFiling; }
    public void setAutoGSTFiling(Boolean autoGSTFiling) { this.autoGSTFiling = autoGSTFiling; }
    
    public Boolean getAutoPayroll() { return autoPayroll; }
    public void setAutoPayroll(Boolean autoPayroll) { this.autoPayroll = autoPayroll; }
    
    public Boolean getAutoInvoice() { return autoInvoice; }
    public void setAutoInvoice(Boolean autoInvoice) { this.autoInvoice = autoInvoice; }
    
    public Boolean getAutoInventory() { return autoInventory; }
    public void setAutoInventory(Boolean autoInventory) { this.autoInventory = autoInventory; }
    
    public Boolean getAutoTax() { return autoTax; }
    public void setAutoTax(Boolean autoTax) { this.autoTax = autoTax; }
    
    public Boolean getAutoCompliance() { return autoCompliance; }
    public void setAutoCompliance(Boolean autoCompliance) { this.autoCompliance = autoCompliance; }
    
    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    
    public String getLastRunStatus() { return lastRunStatus; }
    public void setLastRunStatus(String lastRunStatus) { this.lastRunStatus = lastRunStatus; }
    
    public LocalDateTime getLastRunDate() { return lastRunDate; }
    public void setLastRunDate(LocalDateTime lastRunDate) { this.lastRunDate = lastRunDate; }
    
    public String getConfiguration() { return configuration; }
    public void setConfiguration(String configuration) { this.configuration = configuration; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
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
        autoPilotLevel = "BASIC";
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
