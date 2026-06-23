package com.bharaterp.model.manufacturing;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "bill_of_materials")
public class BillOfMaterials {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String bomName;
    
    @Column(nullable = false, unique = true)
    private String bomCode;
    
    @Column(nullable = false)
    private Long productId;
    
    private String productName;
    private String productCode;
    
    private String version;
    
    @Column(nullable = false)
    private Double quantity = 1.0;
    
    private String unit;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalCost = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal laborCost = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal overheadCost = BigDecimal.ZERO;
    
    private String status = "ACTIVE"; // ACTIVE, INACTIVE, OBSOLETE
    
    private String description;
    
    private Long parentBomId; // For multi-level BOM
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getBomName() { return bomName; }
    public void setBomName(String bomName) { this.bomName = bomName; }
    
    public String getBomCode() { return bomCode; }
    public void setBomCode(String bomCode) { this.bomCode = bomCode; }
    
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    
    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }
    
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    
    public BigDecimal getTotalCost() { return totalCost; }
    public void setTotalCost(BigDecimal totalCost) { this.totalCost = totalCost; }
    
    public BigDecimal getLaborCost() { return laborCost; }
    public void setLaborCost(BigDecimal laborCost) { this.laborCost = laborCost; }
    
    public BigDecimal getOverheadCost() { return overheadCost; }
    public void setOverheadCost(BigDecimal overheadCost) { this.overheadCost = overheadCost; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public Long getParentBomId() { return parentBomId; }
    public void setParentBomId(Long parentBomId) { this.parentBomId = parentBomId; }
    
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
