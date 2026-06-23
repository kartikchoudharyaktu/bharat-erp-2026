package com.bharaterp.model.ai;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_models")
public class AIModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String modelName;
    
    @Column(nullable = false)
    private String modelType; // GENERATIVE, AGENTIC, PREDICTIVE, ANALYTICS
    
    private String version;
    private String description;
    
    private String status = "TRAINING"; // TRAINING, READY, DEPLOYED, FAILED
    
    private Double accuracy = 0.0;
    private Double precision = 0.0;
    private Double recall = 0.0;
    private Double f1Score = 0.0;
    
    private Long trainedBy;
    private LocalDateTime trainingDate;
    private LocalDateTime deploymentDate;
    
    private String inputSchema;
    private String outputSchema;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }
    
    public String getModelType() { return modelType; }
    public void setModelType(String modelType) { this.modelType = modelType; }
    
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Double getAccuracy() { return accuracy; }
    public void setAccuracy(Double accuracy) { this.accuracy = accuracy; }
    
    public Double getPrecision() { return precision; }
    public void setPrecision(Double precision) { this.precision = precision; }
    
    public Double getRecall() { return recall; }
    public void setRecall(Double recall) { this.recall = recall; }
    
    public Double getF1Score() { return f1Score; }
    public void setF1Score(Double f1Score) { this.f1Score = f1Score; }
    
    public Long getTrainedBy() { return trainedBy; }
    public void setTrainedBy(Long trainedBy) { this.trainedBy = trainedBy; }
    
    public LocalDateTime getTrainingDate() { return trainingDate; }
    public void setTrainingDate(LocalDateTime trainingDate) { this.trainingDate = trainingDate; }
    
    public LocalDateTime getDeploymentDate() { return deploymentDate; }
    public void setDeploymentDate(LocalDateTime deploymentDate) { this.deploymentDate = deploymentDate; }
    
    public String getInputSchema() { return inputSchema; }
    public void setInputSchema(String inputSchema) { this.inputSchema = inputSchema; }
    
    public String getOutputSchema() { return outputSchema; }
    public void setOutputSchema(String outputSchema) { this.outputSchema = outputSchema; }
    
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
