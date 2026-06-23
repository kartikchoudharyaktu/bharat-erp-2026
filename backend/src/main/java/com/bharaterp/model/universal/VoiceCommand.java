package com.bharaterp.model.universal;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "voice_commands")
public class VoiceCommand {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String commandText;
    
    @Column(nullable = false)
    private String commandType; // QUERY, ACTION, NAVIGATION, REPORT
    
    @Column(nullable = false)
    private String languageCode; // HI, EN, BN, TA, TE, etc.
    
    private String module; // FINANCE, HR, INVENTORY, SALES, etc.
    
    private String action;
    private String parameters; // JSON
    
    private String response;
    
    private Double confidence = 0.0;
    
    private Long userId;
    
    private String deviceId;
    private String deviceType; // MOBILE, SMART_SPEAKER, CAR, WEB
    
    private String status = "PROCESSED"; // PROCESSING, PROCESSED, FAILED
    
    private String errorMessage;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCommandText() { return commandText; }
    public void setCommandText(String commandText) { this.commandText = commandText; }
    
    public String getCommandType() { return commandType; }
    public void setCommandType(String commandType) { this.commandType = commandType; }
    
    public String getLanguageCode() { return languageCode; }
    public void setLanguageCode(String languageCode) { this.languageCode = languageCode; }
    
    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }
    
    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }
    
    public String getParameters() { return parameters; }
    public void setParameters(String parameters) { this.parameters = parameters; }
    
    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }
    
    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getDeviceId() { return deviceId; }
    public void setDeviceId(String deviceId) { this.deviceId = deviceId; }
    
    public String getDeviceType() { return deviceType; }
    public void setDeviceType(String deviceType) { this.deviceType = deviceType; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    
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
