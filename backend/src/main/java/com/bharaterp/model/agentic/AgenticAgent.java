package com.bharaterp.model.agentic;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agentic_agents")
public class AgenticAgent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String agentName;
    
    @Column(nullable = false)
    private String agentType; // INVOICE_APPROVER, PO_GENERATOR, RECONCILIATOR, EMAIL_PROCESSOR, SCHEDULER
    
    private String description;
    
    private String triggerCondition; // JSON condition
    
    private String actionConfig; // JSON action configuration
    
    private Boolean isActive = true;
    
    private String executionSchedule; // CRON expression
    
    private Integer priority = 5; // 1-10, 10 highest
    
    private String status = "IDLE"; // IDLE, RUNNING, COMPLETED, FAILED
    
    private LocalDateTime lastExecutionAt;
    private String lastExecutionResult;
    
    private Integer successCount = 0;
    private Integer failureCount = 0;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getAgentName() { return agentName; }
    public void setAgentName(String agentName) { this.agentName = agentName; }
    
    public String getAgentType() { return agentType; }
    public void setAgentType(String agentType) { this.agentType = agentType; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getTriggerCondition() { return triggerCondition; }
    public void setTriggerCondition(String triggerCondition) { this.triggerCondition = triggerCondition; }
    
    public String getActionConfig() { return actionConfig; }
    public void setActionConfig(String actionConfig) { this.actionConfig = actionConfig; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public String getExecutionSchedule() { return executionSchedule; }
    public void setExecutionSchedule(String executionSchedule) { this.executionSchedule = executionSchedule; }
    
    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getLastExecutionAt() { return lastExecutionAt; }
    public void setLastExecutionAt(LocalDateTime lastExecutionAt) { this.lastExecutionAt = lastExecutionAt; }
    
    public String getLastExecutionResult() { return lastExecutionResult; }
    public void setLastExecutionResult(String lastExecutionResult) { this.lastExecutionResult = lastExecutionResult; }
    
    public Integer getSuccessCount() { return successCount; }
    public void setSuccessCount(Integer successCount) { this.successCount = successCount; }
    
    public Integer getFailureCount() { return failureCount; }
    public void setFailureCount(Integer failureCount) { this.failureCount = failureCount; }
    
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
