package com.bharaterp.model.mfg_adv;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mixed_mode_manufacturing")
public class MixedModeManufacturing {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String productionOrderId;
    
    @Column(nullable = false)
    private String mode; // MTO, MTS, ATO, ETO, CTO
    
    private String description;
    
    private String status = "ACTIVE"; // ACTIVE, INACTIVE
    
    private String configuration; // JSON
    
    private String customerSpecificRequirements; // JSON
    
    private String engineeringChanges; // JSON
    
    private String notes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getProductionOrderId() { return productionOrderId; }
    public void setProductionOrderId(String productionOrderId) { this.productionOrderId = productionOrderId; }
    
    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getConfiguration() { return configuration; }
    public void setConfiguration(String configuration) { this.configuration = configuration; }
    
    public String getCustomerSpecificRequirements() { return customerSpecificRequirements; }
    public void setCustomerSpecificRequirements(String customerSpecificRequirements) { this.customerSpecificRequirements = customerSpecificRequirements; }
    
    public String getEngineeringChanges() { return engineeringChanges; }
    public void setEngineeringChanges(String engineeringChanges) { this.engineeringChanges = engineeringChanges; }
    
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
