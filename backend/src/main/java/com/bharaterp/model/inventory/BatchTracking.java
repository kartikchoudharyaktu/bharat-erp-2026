package com.bharaterp.model.inventory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "batch_tracking")
public class BatchTracking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long productId;
    
    @Column(nullable = false, unique = true)
    private String batchNumber;
    
    @Column(nullable = false)
    private String batchName;
    
    private Double quantity = 0.0;
    private Double usedQuantity = 0.0;
    private Double availableQuantity = 0.0;
    
    private LocalDate manufacturingDate;
    private LocalDate expiryDate;
    
    private String supplierName;
    private String supplierInvoiceNumber;
    private LocalDate purchaseDate;
    
    private Double purchasePrice = 0.0;
    private Double sellingPrice = 0.0;
    
    private String warehouseLocation;
    private String rackNumber;
    private String binNumber;
    
    private String status = "ACTIVE"; // ACTIVE, EXPIRED, CONSUMED, BLOCKED
    
    private String serialNumbers; // Comma separated serial numbers
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    
    public String getBatchNumber() { return batchNumber; }
    public void setBatchNumber(String batchNumber) { this.batchNumber = batchNumber; }
    
    public String getBatchName() { return batchName; }
    public void setBatchName(String batchName) { this.batchName = batchName; }
    
    public Double getQuantity() { return quantity; }
    public void setQuantity(Double quantity) { this.quantity = quantity; }
    
    public Double getUsedQuantity() { return usedQuantity; }
    public void setUsedQuantity(Double usedQuantity) { this.usedQuantity = usedQuantity; }
    
    public Double getAvailableQuantity() { return availableQuantity; }
    public void setAvailableQuantity(Double availableQuantity) { this.availableQuantity = availableQuantity; }
    
    public LocalDate getManufacturingDate() { return manufacturingDate; }
    public void setManufacturingDate(LocalDate manufacturingDate) { this.manufacturingDate = manufacturingDate; }
    
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }
    
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    
    public String getSupplierInvoiceNumber() { return supplierInvoiceNumber; }
    public void setSupplierInvoiceNumber(String supplierInvoiceNumber) { this.supplierInvoiceNumber = supplierInvoiceNumber; }
    
    public LocalDate getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(LocalDate purchaseDate) { this.purchaseDate = purchaseDate; }
    
    public Double getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(Double purchasePrice) { this.purchasePrice = purchasePrice; }
    
    public Double getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(Double sellingPrice) { this.sellingPrice = sellingPrice; }
    
    public String getWarehouseLocation() { return warehouseLocation; }
    public void setWarehouseLocation(String warehouseLocation) { this.warehouseLocation = warehouseLocation; }
    
    public String getRackNumber() { return rackNumber; }
    public void setRackNumber(String rackNumber) { this.rackNumber = rackNumber; }
    
    public String getBinNumber() { return binNumber; }
    public void setBinNumber(String binNumber) { this.binNumber = binNumber; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getSerialNumbers() { return serialNumbers; }
    public void setSerialNumbers(String serialNumbers) { this.serialNumbers = serialNumbers; }
    
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
