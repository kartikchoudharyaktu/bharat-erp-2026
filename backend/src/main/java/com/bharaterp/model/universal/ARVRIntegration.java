package com.bharaterp.model.universal;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "arvr_integration")
public class ARVRIntegration {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String experienceName;
    
    @Column(nullable = false)
    private String experienceType; // AR, VR, MR
    
    private String platform; // WEBXR, OCULUS, HOLOLENS, ARKIT, ARCORE
    
    private String sceneData; // JSON
    
    private String interactionType; // GESTURE, VOICE, CONTROLLER, EYE_TRACKING
    
    private String assets; // JSON array
    
    private String configuration; // JSON
    
    private String status = "ACTIVE"; // ACTIVE, INACTIVE, DEPLOYED
    
    private String deploymentUrl;
    private String qrCode;
    
    private String notes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getExperienceName() { return experienceName; }
    public void setExperienceName(String experienceName) { this.experienceName = experienceName; }
    
    public String getExperienceType() { return experienceType; }
    public void setExperienceType(String experienceType) { this.experienceType = experienceType; }
    
    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }
    
    public String getSceneData() { return sceneData; }
    public void setSceneData(String sceneData) { this.sceneData = sceneData; }
    
    public String getInteractionType() { return interactionType; }
    public void setInteractionType(String interactionType) { this.interactionType = interactionType; }
    
    public String getAssets() { return assets; }
    public void setAssets(String assets) { this.assets = assets; }
    
    public String getConfiguration() { return configuration; }
    public void setConfiguration(String configuration) { this.configuration = configuration; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getDeploymentUrl() { return deploymentUrl; }
    public void setDeploymentUrl(String deploymentUrl) { this.deploymentUrl = deploymentUrl; }
    
    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
    
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
