package com.bharaterp.model.ai;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "ai_predictions")
public class AIPrediction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String predictionType; // SALES, DEMAND, CASH_FLOW, INVENTORY
    
    private String module;
    
    @Column(nullable = false)
    private String predictionFor; // Product, Customer, Account, etc.
    
    private Long referenceId;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal predictedValue = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal actualValue = BigDecimal.ZERO;
    
    private Double confidence = 0.0;
    
    private String timeFrame; // DAILY, WEEKLY, MONTHLY, QUARTERLY
    private LocalDateTime predictionDate;
    private LocalDateTime validationDate;
    
    private String status = "PENDING"; // PENDING, VALIDATED, INVALID
    
    private String factorsConsidered;
    private String remarks;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getPredictionType() { return predictionType; }
    public void setPredictionType(String predictionType) { this.predictionType = predictionType; }
    
    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }
    
    public String getPredictionFor() { return predictionFor; }
    public void setPredictionFor(String predictionFor) { this.predictionFor = predictionFor; }
    
    public Long getReferenceId() { return referenceId; }
    public void setReferenceId(Long referenceId) { this.referenceId = referenceId; }
    
    public BigDecimal getPredictedValue() { return predictedValue; }
    public void setPredictedValue(BigDecimal predictedValue) { this.predictedValue = predictedValue; }
    
    public BigDecimal getActualValue() { return actualValue; }
    public void setActualValue(BigDecimal actualValue) { this.actualValue = actualValue; }
    
    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }
    
    public String getTimeFrame() { return timeFrame; }
    public void setTimeFrame(String timeFrame) { this.timeFrame = timeFrame; }
    
    public LocalDateTime getPredictionDate() { return predictionDate; }
    public void setPredictionDate(LocalDateTime predictionDate) { this.predictionDate = predictionDate; }
    
    public LocalDateTime getValidationDate() { return validationDate; }
    public void setValidationDate(LocalDateTime validationDate) { this.validationDate = validationDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getFactorsConsidered() { return factorsConsidered; }
    public void setFactorsConsidered(String factorsConsidered) { this.factorsConsidered = factorsConsidered; }
    
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
        if (predictionDate == null) {
            predictionDate = LocalDateTime.now();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
