package com.bharaterp.model.tax;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "tax_compliance")
public class TaxCompliance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String complianceType; // GST, TDS, TCS, PF, ESI
    
    @Column(nullable = false)
    private String period; // MONTHLY, QUARTERLY, ANNUAL
    
    private String month;
    private String year;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal taxableAmount = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal taxAmount = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal interestAmount = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal penaltyAmount = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalAmount = BigDecimal.ZERO;
    
    private String filingStatus; // PENDING, FILED, REJECTED
    
    private LocalDateTime filingDate;
    private LocalDateTime dueDate;
    
    private String filedBy;
    private String acknowledgementNumber;
    private String remarks;
    
    private Long companyId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getComplianceType() { return complianceType; }
    public void setComplianceType(String complianceType) { this.complianceType = complianceType; }
    
    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }
    
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
    
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    
    public BigDecimal getTaxableAmount() { return taxableAmount; }
    public void setTaxableAmount(BigDecimal taxableAmount) { this.taxableAmount = taxableAmount; }
    
    public BigDecimal getTaxAmount() { return taxAmount; }
    public void setTaxAmount(BigDecimal taxAmount) { this.taxAmount = taxAmount; }
    
    public BigDecimal getInterestAmount() { return interestAmount; }
    public void setInterestAmount(BigDecimal interestAmount) { this.interestAmount = interestAmount; }
    
    public BigDecimal getPenaltyAmount() { return penaltyAmount; }
    public void setPenaltyAmount(BigDecimal penaltyAmount) { this.penaltyAmount = penaltyAmount; }
    
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    
    public String getFilingStatus() { return filingStatus; }
    public void setFilingStatus(String filingStatus) { this.filingStatus = filingStatus; }
    
    public LocalDateTime getFilingDate() { return filingDate; }
    public void setFilingDate(LocalDateTime filingDate) { this.filingDate = filingDate; }
    
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    
    public String getFiledBy() { return filedBy; }
    public void setFiledBy(String filedBy) { this.filedBy = filedBy; }
    
    public String getAcknowledgementNumber() { return acknowledgementNumber; }
    public void setAcknowledgementNumber(String acknowledgementNumber) { this.acknowledgementNumber = acknowledgementNumber; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
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
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
