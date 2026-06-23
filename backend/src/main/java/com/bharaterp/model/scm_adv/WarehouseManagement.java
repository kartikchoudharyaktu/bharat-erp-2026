package com.bharaterp.model.scm_adv;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_management")
public class WarehouseManagement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String warehouseCode;
    
    @Column(nullable = false)
    private String warehouseName;
    
    private String warehouseType; // CENTRAL, REGIONAL, LOCAL, RETAIL, ECOMMERCE
    
    private Double totalArea = 0.0;
    private Double usedArea = 0.0;
    private Double availableArea = 0.0;
    
    private Integer totalRacks = 0;
    private Integer usedRacks = 0;
    private Integer availableRacks = 0;
    
    private Integer totalBins = 0;
    private Integer usedBins = 0;
    private Integer availableBins = 0;
    
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String country;
    
    private Double latitude;
    private Double longitude;
    
    private String contactPerson;
    private String contactPhone;
    private String contactEmail;
    
    private String status = "ACTIVE"; // ACTIVE, INACTIVE, MAINTENANCE
    
    private String automationLevel; // MANUAL, SEMI_AUTOMATED, FULLY_AUTOMATED
    
    private String equipment; // JSON list of equipment
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getWarehouseCode() { return warehouseCode; }
    public void setWarehouseCode(String warehouseCode) { this.warehouseCode = warehouseCode; }
    
    public String getWarehouseName() { return warehouseName; }
    public void setWarehouseName(String warehouseName) { this.warehouseName = warehouseName; }
    
    public String getWarehouseType() { return warehouseType; }
    public void setWarehouseType(String warehouseType) { this.warehouseType = warehouseType; }
    
    public Double getTotalArea() { return totalArea; }
    public void setTotalArea(Double totalArea) { this.totalArea = totalArea; }
    
    public Double getUsedArea() { return usedArea; }
    public void setUsedArea(Double usedArea) { this.usedArea = usedArea; }
    
    public Double getAvailableArea() { return availableArea; }
    public void setAvailableArea(Double availableArea) { this.availableArea = availableArea; }
    
    public Integer getTotalRacks() { return totalRacks; }
    public void setTotalRacks(Integer totalRacks) { this.totalRacks = totalRacks; }
    
    public Integer getUsedRacks() { return usedRacks; }
    public void setUsedRacks(Integer usedRacks) { this.usedRacks = usedRacks; }
    
    public Integer getAvailableRacks() { return availableRacks; }
    public void setAvailableRacks(Integer availableRacks) { this.availableRacks = availableRacks; }
    
    public Integer getTotalBins() { return totalBins; }
    public void setTotalBins(Integer totalBins) { this.totalBins = totalBins; }
    
    public Integer getUsedBins() { return usedBins; }
    public void setUsedBins(Integer usedBins) { this.usedBins = usedBins; }
    
    public Integer getAvailableBins() { return availableBins; }
    public void setAvailableBins(Integer availableBins) { this.availableBins = availableBins; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }
    
    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }
    
    public String getContactPerson() { return contactPerson; }
    public void setContactPerson(String contactPerson) { this.contactPerson = contactPerson; }
    
    public String getContactPhone() { return contactPhone; }
    public void setContactPhone(String contactPhone) { this.contactPhone = contactPhone; }
    
    public String getContactEmail() { return contactEmail; }
    public void setContactEmail(String contactEmail) { this.contactEmail = contactEmail; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getAutomationLevel() { return automationLevel; }
    public void setAutomationLevel(String automationLevel) { this.automationLevel = automationLevel; }
    
    public String getEquipment() { return equipment; }
    public void setEquipment(String equipment) { this.equipment = equipment; }
    
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
        if (warehouseCode == null || warehouseCode.isEmpty()) {
            warehouseCode = "WH-" + System.currentTimeMillis();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
