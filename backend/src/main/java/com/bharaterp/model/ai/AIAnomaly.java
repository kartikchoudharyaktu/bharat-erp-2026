package com.bharaterp.model.ai;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "ai_anomalies")
public class AIAnomaly {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String anomalyType; // FRAUD, ERROR, OUTLIER, PATTERN_BREAK
    
    private String module;
    
    @Column(nullable = false)
    private String description;
    
    private String referenceId; // Invoice, Order, Transaction ID
    
    @Column(precision = 19, scale = 2)
    private BigDecimal deviationValue = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal expectedValue = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal actualValue = BigDecimal.ZERO;
    
    private String severity; // LOW, MEDIUM, HIGH, CRITICAL
    
    private String status = "NEW"; // NEW, INVESTIGATING, RESOLVED, FALSE_ALARM
    
    private String investigatedBy;
    private LocalDateTime investigationDate;
    private String resolutionNotes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getAnomalyType() { return anomalyType; }
    public void setAnomalyType(String anomalyType) { this.anomalyType = anomalyType; }
    
    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getReferenceId() { return referenceId; }
    public void setReferenceId(String referenceId) { this.referenceId = referenceId; }
    
    public BigDecimal getDeviationValue() { return deviationValue; }
    public void setDeviationValue(BigDecimal deviationValue) { this.deviationValue = deviationValue; }
    
    public BigDecimal getExpectedValue() { return expectedValue; }
    public void setExpectedValue(BigDecimal expectedValue) { this.expectedValue = expectedValue; }
    
    public BigDecimal getActualValue() { return actualValue; }
    public void setActualValue(BigDecimal actualValue) { this.actualValue = actualValue; }
    
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getInvestigatedBy() { return investigatedBy; }
    public void setInvestigatedBy(String investigatedBy) { this.investigatedBy = investigatedBy; }
    
    public LocalDateTime getInvestigationDate() { return investigationDate; }
    public void setInvestigationDate(LocalDateTime investigationDate) { this.investigationDate = investigationDate; }
    
    public String getResolutionNotes() { return resolutionNotes; }
    public void setResolutionNotes(String resolutionNotes) { this.resolutionNotes = resolutionNotes; }
    
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
