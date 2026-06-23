package com.bharaterp.model.manufacturing;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quality_checks")
public class QualityCheck {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long productionOrderId;
    
    private Long productId;
    private String productName;
    
    private String checkType; // INSPECTION, TESTING, AUDIT
    
    @Column(nullable = false)
    private String checkParameter;
    
    private String expectedValue;
    private String actualValue;
    
    private String result; // PASS, FAIL, PENDING
    
    private String remarks;
    
    private String inspectedBy;
    private LocalDateTime inspectionDate;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getProductionOrderId() { return productionOrderId; }
    public void setProductionOrderId(Long productionOrderId) { this.productionOrderId = productionOrderId; }
    
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public String getCheckType() { return checkType; }
    public void setCheckType(String checkType) { this.checkType = checkType; }
    
    public String getCheckParameter() { return checkParameter; }
    public void setCheckParameter(String checkParameter) { this.checkParameter = checkParameter; }
    
    public String getExpectedValue() { return expectedValue; }
    public void setExpectedValue(String expectedValue) { this.expectedValue = expectedValue; }
    
    public String getActualValue() { return actualValue; }
    public void setActualValue(String actualValue) { this.actualValue = actualValue; }
    
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public String getInspectedBy() { return inspectedBy; }
    public void setInspectedBy(String inspectedBy) { this.inspectedBy = inspectedBy; }
    
    public LocalDateTime getInspectionDate() { return inspectionDate; }
    public void setInspectionDate(LocalDateTime inspectionDate) { this.inspectionDate = inspectionDate; }
    
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (inspectionDate == null) {
            inspectionDate = LocalDateTime.now();
        }
    }
}
