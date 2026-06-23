package com.bharaterp.model.enhancement;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "digital_twins")
public class DigitalTwin {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String twinName;
    
    @Column(nullable = false)
    private String twinType; // PRODUCT, PROCESS, EQUIPMENT, SUPPLY_CHAIN
    
    @Column(nullable = false)
    private Long referenceId; // Product ID, Machine ID, Process ID
    
    private String referenceType; // PRODUCT, MACHINE, PROCESS
    
    private String modelData; // JSON model configuration
    
    private String simulationData; // JSON simulation results
    
    private String status = "ACTIVE"; // ACTIVE, SIMULATING, PAUSED, INACTIVE
    
    private Double accuracy = 0.0;
    private Double confidence = 0.0;
    
    private String lastSimulationResult;
    private LocalDateTime lastSimulationAt;
    
    private String recommendations; // JSON
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTwinName() { return twinName; }
    public void setTwinName(String twinName) { this.twinName = twinName; }
    
    public String getTwinType() { return twinType; }
    public void setTwinType(String twinType) { this.twinType = twinType; }
    
    public Long getReferenceId() { return referenceId; }
    public void setReferenceId(Long referenceId) { this.referenceId = referenceId; }
    
    public String getReferenceType() { return referenceType; }
    public void setReferenceType(String referenceType) { this.referenceType = referenceType; }
    
    public String getModelData() { return modelData; }
    public void setModelData(String modelData) { this.modelData = modelData; }
    
    public String getSimulationData() { return simulationData; }
    public void setSimulationData(String simulationData) { this.simulationData = simulationData; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Double getAccuracy() { return accuracy; }
    public void setAccuracy(Double accuracy) { this.accuracy = accuracy; }
    
    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }
    
    public String getLastSimulationResult() { return lastSimulationResult; }
    public void setLastSimulationResult(String lastSimulationResult) { this.lastSimulationResult = lastSimulationResult; }
    
    public LocalDateTime getLastSimulationAt() { return lastSimulationAt; }
    public void setLastSimulationAt(LocalDateTime lastSimulationAt) { this.lastSimulationAt = lastSimulationAt; }
    
    public String getRecommendations() { return recommendations; }
    public void setRecommendations(String recommendations) { this.recommendations = recommendations; }
    
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
