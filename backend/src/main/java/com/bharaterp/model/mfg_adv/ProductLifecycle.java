package com.bharaterp.model.mfg_adv;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_lifecycle")
public class ProductLifecycle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String productCode;
    
    @Column(nullable = false)
    private String productName;
    
    @Column(nullable = false)
    private String stage; // CONCEPT, DESIGN, DEVELOPMENT, TESTING, PRODUCTION, MATURITY, DECLINE, RETIRE
    
    private String version;
    
    private String category;
    private String subCategory;
    
    private String targetMarket;
    private String targetAudience;
    
    private LocalDateTime conceptDate;
    private LocalDateTime designDate;
    private LocalDateTime developmentDate;
    private LocalDateTime launchDate;
    private LocalDateTime maturityDate;
    private LocalDateTime declineDate;
    private LocalDateTime retirementDate;
    
    private String status = "ACTIVE"; // ACTIVE, INACTIVE, RETIRED
    
    private String specifications; // JSON
    
    private String billOfMaterials; // JSON BOM reference
    
    private String qualityStandards; // JSON
    
    private String regulatoryCompliance; // JSON
    
    private String notes;
    
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
    
    public String getStage() { return stage; }
    public void setStage(String stage) { this.stage = stage; }
    
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getSubCategory() { return subCategory; }
    public void setSubCategory(String subCategory) { this.subCategory = subCategory; }
    
    public String getTargetMarket() { return targetMarket; }
    public void setTargetMarket(String targetMarket) { this.targetMarket = targetMarket; }
    
    public String getTargetAudience() { return targetAudience; }
    public void setTargetAudience(String targetAudience) { this.targetAudience = targetAudience; }
    
    public LocalDateTime getConceptDate() { return conceptDate; }
    public void setConceptDate(LocalDateTime conceptDate) { this.conceptDate = conceptDate; }
    
    public LocalDateTime getDesignDate() { return designDate; }
    public void setDesignDate(LocalDateTime designDate) { this.designDate = designDate; }
    
    public LocalDateTime getDevelopmentDate() { return developmentDate; }
    public void setDevelopmentDate(LocalDateTime developmentDate) { this.developmentDate = developmentDate; }
    
    public LocalDateTime getLaunchDate() { return launchDate; }
    public void setLaunchDate(LocalDateTime launchDate) { this.launchDate = launchDate; }
    
    public LocalDateTime getMaturityDate() { return maturityDate; }
    public void setMaturityDate(LocalDateTime maturityDate) { this.maturityDate = maturityDate; }
    
    public LocalDateTime getDeclineDate() { return declineDate; }
    public void setDeclineDate(LocalDateTime declineDate) { this.declineDate = declineDate; }
    
    public LocalDateTime getRetirementDate() { return retirementDate; }
    public void setRetirementDate(LocalDateTime retirementDate) { this.retirementDate = retirementDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getSpecifications() { return specifications; }
    public void setSpecifications(String specifications) { this.specifications = specifications; }
    
    public String getBillOfMaterials() { return billOfMaterials; }
    public void setBillOfMaterials(String billOfMaterials) { this.billOfMaterials = billOfMaterials; }
    
    public String getQualityStandards() { return qualityStandards; }
    public void setQualityStandards(String qualityStandards) { this.qualityStandards = qualityStandards; }
    
    public String getRegulatoryCompliance() { return regulatoryCompliance; }
    public void setRegulatoryCompliance(String regulatoryCompliance) { this.regulatoryCompliance = regulatoryCompliance; }
    
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
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
