package com.bharaterp.model.innovation;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "hyper_localization")
public class HyperLocalization {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String locationCode; // State/UT code
    
    @Column(nullable = false)
    private String locationName;
    
    private String locationType; // STATE, UT, DISTRICT, CITY
    
    private String state;
    private String district;
    private String city;
    private String pincode;
    
    private String region; // NORTH, SOUTH, EAST, WEST, CENTRAL
    
    private String language; // Primary language
    
    private String taxRules; // JSON
    
    private String complianceRules; // JSON
    
    private String localLaws; // JSON
    
    private String culturalPreferences; // JSON
    
    private String configuration; // JSON
    
    private Boolean isActive = true;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getLocationCode() { return locationCode; }
    public void setLocationCode(String locationCode) { this.locationCode = locationCode; }
    
    public String getLocationName() { return locationName; }
    public void setLocationName(String locationName) { this.locationName = locationName; }
    
    public String getLocationType() { return locationType; }
    public void setLocationType(String locationType) { this.locationType = locationType; }
    
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }
    
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    
    public String getTaxRules() { return taxRules; }
    public void setTaxRules(String taxRules) { this.taxRules = taxRules; }
    
    public String getComplianceRules() { return complianceRules; }
    public void setComplianceRules(String complianceRules) { this.complianceRules = complianceRules; }
    
    public String getLocalLaws() { return localLaws; }
    public void setLocalLaws(String localLaws) { this.localLaws = localLaws; }
    
    public String getCulturalPreferences() { return culturalPreferences; }
    public void setCulturalPreferences(String culturalPreferences) { this.culturalPreferences = culturalPreferences; }
    
    public String getConfiguration() { return configuration; }
    public void setConfiguration(String configuration) { this.configuration = configuration; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
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
