package com.bharaterp.model.finance_adv;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "cost_accounting")
public class CostAccounting {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String costCenterCode;
    
    @Column(nullable = false)
    private String costCenterName;
    
    private String costCenterType; // DEPARTMENT, PROJECT, PRODUCT, REGION, CHANNEL
    
    @Column(nullable = false)
    private String costElement; // DIRECT_MATERIAL, DIRECT_LABOR, OVERHEAD, ADMIN, SELLING
    
    @Column(precision = 19, scale = 2)
    private BigDecimal actualCost = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal budgetedCost = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal variance = BigDecimal.ZERO;
    
    private Double variancePercentage = 0.0;
    
    private String allocationMethod; // DIRECT, STEP_DOWN, RECIPROCAL, ACTIVITY_BASED
    
    @Column(nullable = false)
    private String period; // MONTHLY, QUARTERLY, ANNUAL
    
    private String month;
    private String year;
    
    private Long costDriverId; // Product, Activity, Resource
    
    private String activityName;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal costDriverRate = BigDecimal.ZERO;
    
    private Double driverQuantity = 0.0;
    
    private String notes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCostCenterCode() { return costCenterCode; }
    public void setCostCenterCode(String costCenterCode) { this.costCenterCode = costCenterCode; }
    
    public String getCostCenterName() { return costCenterName; }
    public void setCostCenterName(String costCenterName) { this.costCenterName = costCenterName; }
    
    public String getCostCenterType() { return costCenterType; }
    public void setCostCenterType(String costCenterType) { this.costCenterType = costCenterType; }
    
    public String getCostElement() { return costElement; }
    public void setCostElement(String costElement) { this.costElement = costElement; }
    
    public BigDecimal getActualCost() { return actualCost; }
    public void setActualCost(BigDecimal actualCost) { this.actualCost = actualCost; }
    
    public BigDecimal getBudgetedCost() { return budgetedCost; }
    public void setBudgetedCost(BigDecimal budgetedCost) { this.budgetedCost = budgetedCost; }
    
    public BigDecimal getVariance() { return variance; }
    public void setVariance(BigDecimal variance) { this.variance = variance; }
    
    public Double getVariancePercentage() { return variancePercentage; }
    public void setVariancePercentage(Double variancePercentage) { this.variancePercentage = variancePercentage; }
    
    public String getAllocationMethod() { return allocationMethod; }
    public void setAllocationMethod(String allocationMethod) { this.allocationMethod = allocationMethod; }
    
    public String getPeriod() { return period; }
    public void setPeriod(String period) { this.period = period; }
    
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
    
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    
    public Long getCostDriverId() { return costDriverId; }
    public void setCostDriverId(Long costDriverId) { this.costDriverId = costDriverId; }
    
    public String getActivityName() { return activityName; }
    public void setActivityName(String activityName) { this.activityName = activityName; }
    
    public BigDecimal getCostDriverRate() { return costDriverRate; }
    public void setCostDriverRate(BigDecimal costDriverRate) { this.costDriverRate = costDriverRate; }
    
    public Double getDriverQuantity() { return driverQuantity; }
    public void setDriverQuantity(Double driverQuantity) { this.driverQuantity = driverQuantity; }
    
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
        if (costCenterCode == null || costCenterCode.isEmpty()) {
            costCenterCode = "CC-" + System.currentTimeMillis();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
