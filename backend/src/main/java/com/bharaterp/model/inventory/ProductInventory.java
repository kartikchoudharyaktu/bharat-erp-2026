package com.bharaterp.model.inventory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "product_inventory")
public class ProductInventory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String productCode;
    
    @Column(nullable = false)
    private String productName;
    
    private String category;
    private String subCategory;
    private String brand;
    private String unit;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal purchasePrice = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal sellingPrice = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal mrp = BigDecimal.ZERO;
    
    private Double currentStock = 0.0;
    private Double minStock = 0.0;
    private Double maxStock = 0.0;
    private Double reorderLevel = 0.0;
    
    private String hsnCode;
    private Double gstRate = 18.0;
    
    private String warehouseLocation;
    private String rackNumber;
    private String binNumber;
    
    @Column(length = 500)
    private String description;
    
    private String status = "ACTIVE"; // ACTIVE, INACTIVE, DISCONTINUED
    
    private String barcode;
    private String rfidTag;
    
    private Boolean isBatchTracked = false;
    private Boolean isSerialTracked = false;
    private Boolean isExpiryTracked = false;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getSubCategory() { return subCategory; }
    public void setSubCategory(String subCategory) { this.subCategory = subCategory; }
    
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    
    public BigDecimal getPurchasePrice() { return purchasePrice; }
    public void setPurchasePrice(BigDecimal purchasePrice) { this.purchasePrice = purchasePrice; }
    
    public BigDecimal getSellingPrice() { return sellingPrice; }
    public void setSellingPrice(BigDecimal sellingPrice) { this.sellingPrice = sellingPrice; }
    
    public BigDecimal getMrp() { return mrp; }
    public void setMrp(BigDecimal mrp) { this.mrp = mrp; }
    
    public Double getCurrentStock() { return currentStock; }
    public void setCurrentStock(Double currentStock) { this.currentStock = currentStock; }
    
    public Double getMinStock() { return minStock; }
    public void setMinStock(Double minStock) { this.minStock = minStock; }
    
    public Double getMaxStock() { return maxStock; }
    public void setMaxStock(Double maxStock) { this.maxStock = maxStock; }
    
    public Double getReorderLevel() { return reorderLevel; }
    public void setReorderLevel(Double reorderLevel) { this.reorderLevel = reorderLevel; }
    
    public String getHsnCode() { return hsnCode; }
    public void setHsnCode(String hsnCode) { this.hsnCode = hsnCode; }
    
    public Double getGstRate() { return gstRate; }
    public void setGstRate(Double gstRate) { this.gstRate = gstRate; }
    
    public String getWarehouseLocation() { return warehouseLocation; }
    public void setWarehouseLocation(String warehouseLocation) { this.warehouseLocation = warehouseLocation; }
    
    public String getRackNumber() { return rackNumber; }
    public void setRackNumber(String rackNumber) { this.rackNumber = rackNumber; }
    
    public String getBinNumber() { return binNumber; }
    public void setBinNumber(String binNumber) { this.binNumber = binNumber; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }
    
    public String getRfidTag() { return rfidTag; }
    public void setRfidTag(String rfidTag) { this.rfidTag = rfidTag; }
    
    public Boolean getIsBatchTracked() { return isBatchTracked; }
    public void setIsBatchTracked(Boolean isBatchTracked) { this.isBatchTracked = isBatchTracked; }
    
    public Boolean getIsSerialTracked() { return isSerialTracked; }
    public void setIsSerialTracked(Boolean isSerialTracked) { this.isSerialTracked = isSerialTracked; }
    
    public Boolean getIsExpiryTracked() { return isExpiryTracked; }
    public void setIsExpiryTracked(Boolean isExpiryTracked) { this.isExpiryTracked = isExpiryTracked; }
    
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
