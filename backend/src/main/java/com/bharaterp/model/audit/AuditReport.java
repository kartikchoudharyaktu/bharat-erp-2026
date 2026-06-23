package com.bharaterp.model.audit;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "audit_reports")
public class AuditReport {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String reportNumber;
    
    @Column(nullable = false)
    private String reportType; // STATUTORY, INTERNAL, TAX, GST, COMPLIANCE, FORENSIC
    
    @Column(nullable = false)
    private String auditPeriod;
    private String financialYear;
    
    @Column(nullable = false)
    private String auditorName;
    private String auditorFirm;
    private String auditorMembershipNumber;
    
    @Column(length = 4000)
    private String executiveSummary;
    
    @Column(length = 4000)
    private String findings;
    
    @Column(length = 4000)
    private String recommendations;
    
    @Column(length = 4000)
    private String conclusions;
    
    private String opinion; // UNQUALIFIED, QUALIFIED, ADVERSE, DISCLAIMER
    
    private String status = "DRAFT"; // DRAFT, REVIEW, FINAL, PUBLISHED
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalAssets = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalLiabilities = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal netAssets = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalRevenue = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalExpenses = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal netProfit = BigDecimal.ZERO;
    
    private String deviations; // JSON
    private String materialMatters; // JSON
    
    private String attachments; // JSON
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getReportNumber() { return reportNumber; }
    public void setReportNumber(String reportNumber) { this.reportNumber = reportNumber; }
    
    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }
    
    public String getAuditPeriod() { return auditPeriod; }
    public void setAuditPeriod(String auditPeriod) { this.auditPeriod = auditPeriod; }
    
    public String getFinancialYear() { return financialYear; }
    public void setFinancialYear(String financialYear) { this.financialYear = financialYear; }
    
    public String getAuditorName() { return auditorName; }
    public void setAuditorName(String auditorName) { this.auditorName = auditorName; }
    
    public String getAuditorFirm() { return auditorFirm; }
    public void setAuditorFirm(String auditorFirm) { this.auditorFirm = auditorFirm; }
    
    public String getAuditorMembershipNumber() { return auditorMembershipNumber; }
    public void setAuditorMembershipNumber(String auditorMembershipNumber) { this.auditorMembershipNumber = auditorMembershipNumber; }
    
    public String getExecutiveSummary() { return executiveSummary; }
    public void setExecutiveSummary(String executiveSummary) { this.executiveSummary = executiveSummary; }
    
    public String getFindings() { return findings; }
    public void setFindings(String findings) { this.findings = findings; }
    
    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }
    
    public String getConclusions() { return conclusions; }
    public void setConclusions(String conclusions) { this.conclusions = conclusions; }
    
    public String getOpinion() { return opinion; }
    public void setOpinion(String opinion) { this.opinion = opinion; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public BigDecimal getTotalAssets() { return totalAssets; }
    public void setTotalAssets(BigDecimal totalAssets) { this.totalAssets = totalAssets; }
    
    public BigDecimal getTotalLiabilities() { return totalLiabilities; }
    public void setTotalLiabilities(BigDecimal totalLiabilities) { this.totalLiabilities = totalLiabilities; }
    
    public BigDecimal getNetAssets() { return netAssets; }
    public void setNetAssets(BigDecimal netAssets) { this.netAssets = netAssets; }
    
    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
    
    public BigDecimal getTotalExpenses() { return totalExpenses; }
    public void setTotalExpenses(BigDecimal totalExpenses) { this.totalExpenses = totalExpenses; }
    
    public BigDecimal getNetProfit() { return netProfit; }
    public void setNetProfit(BigDecimal netProfit) { this.netProfit = netProfit; }
    
    public String getDeviations() { return deviations; }
    public void setDeviations(String deviations) { this.deviations = deviations; }
    
    public String getMaterialMatters() { return materialMatters; }
    public void setMaterialMatters(String materialMatters) { this.materialMatters = materialMatters; }
    
    public String getAttachments() { return attachments; }
    public void setAttachments(String attachments) { this.attachments = attachments; }
    
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
        if (reportNumber == null || reportNumber.isEmpty()) {
            reportNumber = "AUDIT-" + System.currentTimeMillis();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
