package com.bharaterp.model.inventory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "stock_movement")
public class StockMovement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long productId;
    
    @Column(nullable = false)
    private String movementType; // PURCHASE, SALE, TRANSFER, ADJUSTMENT, RETURN
    
    private String referenceNumber; // PO Number, Invoice Number, etc.
    
    private Double quantity = 0.0;
    private Double previousStock = 0.0;
    private Double newStock = 0.0;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal unitPrice = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalValue = BigDecimal.ZERO;
    
    private String sourceWarehouse;
    private String destinationWarehouse;
    
    private String batchNumber;
    private String serialNumber;
    
    private String reason;
    private String remarks;
    
    private Long createdBy;
    private Long companyId;
    
    private LocalDateTime createdAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public String getMovementType() { return movementType; }
    public void setMovementType(String movementType) { this.movementType = movementType; }
    
    public String getReferenceNumber() { return referenceNumber; }
    public void setReferenceNumber(String referenceNumber) { this.referenceNumber = referenceNumber; }
    
    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }
    
    public Double getPreviousStock() { return previousStock; }
    public void setPreviousStock(Double previousStock) { this.previousStock = previousStock; }
    
    public Double getNewStock() { return newStock; }
    public void setNewStock(Double newStock) { this.newStock = newStock; }
    
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    
    public BigDecimal getTotalValue() { return totalValue; }
    public void setTotalValue(BigDecimal totalValue) { this.totalValue = totalValue; }
    
    public String getSourceWarehouse() { return sourceWarehouse; }
    public void setSourceWarehouse(String sourceWarehouse) { this.sourceWarehouse = sourceWarehouse; }
    
    public String getDestinationWarehouse() { return destinationWarehouse; }
    public void setDestinationWarehouse(String destinationWarehouse) { this.destinationWarehouse = destinationWarehouse; }
    
    public String getBatchNumber() { return batchNumber; }
    public void setBatchNumber(String batchNumber) { this.batchNumber = batchNumber; }
    
    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }
    
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
