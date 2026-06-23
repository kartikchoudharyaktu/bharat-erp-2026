package com.bharaterp.model.audit;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "ca_compliance")
public class CACompliance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String complianceType; // GST, TDS, INCOME_TAX, COMPANY_LAW, AUDIT
    
    @Column(nullable = false)
    private String financialYear;
    private String period; // QUARTERLY, HALF_YEARLY, ANNUAL
    
    private String returnType; // GSTR-1, GSTR-3B, ITR, TDS_RETURN
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalRevenue = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalExpenses = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal netProfit = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal taxLiability = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal taxPaid = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal taxBalance = BigDecimal.ZERO;
    
    private String filingStatus; // PENDING, FILED, REJECTED, COMPLETED
    
    private String filingDate;
    private String dueDate;
    
    private String filedBy; // CA Name
    private String caMembershipNumber;
    private String caFirmName;
    
    private String acknowledgementNumber;
    private String remarks;
    
    private String auditStatus; // NOT_STARTED, IN_PROGRESS, COMPLETED, QUALIFIED
    
    private String auditReport; // JSON
    private String observations; // JSON
    private String recommendations; // JSON
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getComplianceType() { return complianceType; }
    public void setComplianceType(String complianceType) { this.complianceType = complianceType; }
    
    public String getFinancialYear() { return financialYear; }
    public void setFinancialYear(String financialYear) { this.financialYear = financialYear; }
    
    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }
    
    public String getReturnType() { return returnType; }
    public void setReturnType(String returnType) { this.returnType = returnType; }
    
    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
    
    public BigDecimal getTotalExpenses() { return totalExpenses; }
    public void setTotalExpenses(BigDecimal totalExpenses) { this.totalExpenses = totalExpenses; }
    
    public BigDecimal getNetProfit() { return netProfit; }
    public void setNetProfit(BigDecimal netProfit) { this.netProfit = netProfit; }
    
    public BigDecimal getTaxLiability() { return taxLiability; }
    public void setTaxLiability(BigDecimal taxLiability) { this.taxLiability = taxLiability; }
    
    public BigDecimal getTaxPaid() { return taxPaid; }
    public void setTaxPaid(BigDecimal taxPaid) { this.taxPaid = taxPaid; }
    
    public BigDecimal getTaxBalance() { return taxBalance; }
    public void setTaxBalance(BigDecimal taxBalance) { this.taxBalance = taxBalance; }
    
    public String getFilingStatus() { return filingStatus; }
    public void setFilingStatus(String filingStatus) { this.filingStatus = filingStatus; }
    
    public String getFilingDate() { return filingDate; }
    public void setFilingDate(String filingDate) { this.filingDate = filingDate; }
    
    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    
    public String getFiledBy() { return filedBy; }
    public void setFiledBy(String filedBy) { this.filedBy = filedBy; }
    
    public String getCaMembershipNumber() { return caMembershipNumber; }
    public void setCaMembershipNumber(String caMembershipNumber) { this.caMembershipNumber = caMembershipNumber; }
    
    public String getCaFirmName() { return caFirmName; }
    public void setCaFirmName(String caFirmName) { this.caFirmName = caFirmName; }
    
    public String getAcknowledgementNumber() { return acknowledgementNumber; }
    public void setAcknowledgementNumber(String acknowledgementNumber) { this.acknowledgementNumber = acknowledgementNumber; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public String getAuditStatus() { return auditStatus; }
    public void setAuditStatus(String auditStatus) { this.auditStatus = auditStatus; }
    
    public String getAuditReport() { return auditReport; }
    public void setAuditReport(String auditReport) { this.auditReport = auditReport; }
    
    public String getObservations() { return observations; }
    public void setObservations(String observations) { this.observations = observations; }
    
    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }
    
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
