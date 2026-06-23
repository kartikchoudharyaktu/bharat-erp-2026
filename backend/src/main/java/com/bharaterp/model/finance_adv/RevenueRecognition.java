package com.bharaterp.model.finance_adv;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "revenue_recognition")
public class RevenueRecognition {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String contractNumber;
    
    @Column(nullable = false)
    private Long customerId;
    
    private String customerName;
    
    @Column(nullable = false)
    private String revenueType; // PRODUCT, SERVICE, SUBSCRIPTION, LICENSE
    
    @Column(nullable = false)
    private String recognitionMethod; // POC, COMPLETED_CONTRACT, STRAIGHT_LINE, ASC_606
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalContractValue = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal recognizedRevenue = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal deferredRevenue = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal recognizedToDate = BigDecimal.ZERO;
    
    private LocalDateTime contractStartDate;
    private LocalDateTime contractEndDate;
    
    private Integer totalPeriods = 0;
    private Integer completedPeriods = 0;
    
    private String status = "ACTIVE"; // ACTIVE, COMPLETED, CANCELLED
    
    private String accountingStandard; // ASC_606, IFRS_15, IND_AS_115
    
    private String notes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getContractNumber() { return contractNumber; }
    public void setContractNumber(String contractNumber) { this.contractNumber = contractNumber; }
    
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    
    public String getRevenueType() { return revenueType; }
    public void setRevenueType(String revenueType) { this.revenueType = revenueType; }
    
    public String getRecognitionMethod() { return recognitionMethod; }
    public void setRecognitionMethod(String recognitionMethod) { this.recognitionMethod = recognitionMethod; }
    
    public BigDecimal getTotalContractValue() { return totalContractValue; }
    public void setTotalContractValue(BigDecimal totalContractValue) { this.totalContractValue = totalContractValue; }
    
    public BigDecimal getRecognizedRevenue() { return recognizedRevenue; }
    public void setRecognizedRevenue(BigDecimal recognizedRevenue) { this.recognizedRevenue = recognizedRevenue; }
    
    public BigDecimal getDeferredRevenue() { return deferredRevenue; }
    public void setDeferredRevenue(BigDecimal deferredRevenue) { this.deferredRevenue = deferredRevenue; }
    
    public BigDecimal getRecognizedToDate() { return recognizedToDate; }
    public void setRecognizedToDate(BigDecimal recognizedToDate) { this.recognizedToDate = recognizedToDate; }
    
    public LocalDateTime getContractStartDate() { return contractStartDate; }
    public void setContractStartDate(LocalDateTime contractStartDate) { this.contractStartDate = contractStartDate; }
    
    public LocalDateTime getContractEndDate() { return contractEndDate; }
    public void setContractEndDate(LocalDateTime contractEndDate) { this.contractEndDate = contractEndDate; }
    
    public Integer getTotalPeriods() { return totalPeriods; }
    public void setTotalPeriods(Integer totalPeriods) { this.totalPeriods = totalPeriods; }
    
    public Integer getCompletedPeriods() { return completedPeriods; }
    public void setCompletedPeriods(Integer completedPeriods) { this.completedPeriods = completedPeriods; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getAccountingStandard() { return accountingStandard; }
    public void setAccountingStandard(String accountingStandard) { this.accountingStandard = accountingStandard; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
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
        if (contractNumber == null || contractNumber.isEmpty()) {
            contractNumber = "REV-" + System.currentTimeMillis();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
