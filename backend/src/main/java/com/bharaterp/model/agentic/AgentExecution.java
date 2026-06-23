package com.bharaterp.model.agentic;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agent_executions")
public class AgentExecution {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long agentId;
    
    private String agentName;
    private String agentType;
    
    private String executionId;
    
    private String status = "STARTED"; // STARTED, PROCESSING, COMPLETED, FAILED
    
    private String inputData; // JSON
    private String outputData; // JSON
    
    private String resultSummary;
    private String errorMessage;
    
    private Integer itemsProcessed = 0;
    private Integer itemsApproved = 0;
    private Integer itemsRejected = 0;
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    
    private Long triggeredBy; // User ID
    private String triggerSource; // SCHEDULED, MANUAL, EVENT
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getAgentId() { return agentId; }
    public void setAgentId(Long agentId) { this.agentId = agentId; }
    
    public String getAgentName() { return agentName; }
    public void setAgentName(String agentName) { this.agentName = agentName; }
    
    public String getAgentType() { return agentType; }
    public void setAgentType(String agentType) { this.agentType = agentType; }
    
    public String getExecutionId() { return executionId; }
    public void setExecutionId(String executionId) { this.executionId = executionId; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getInputData() { return inputData; }
    public void setInputData(String inputData) { this.inputData = inputData; }
    
    public String getOutputData() { return outputData; }
    public void setOutputData(String outputData) { this.outputData = outputData; }
    
    public String getResultSummary() { return resultSummary; }
    public void setResultSummary(String resultSummary) { this.resultSummary = resultSummary; }
    
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    
    public Integer getItemsProcessed() { return itemsProcessed; }
    public void setItemsProcessed(Integer itemsProcessed) { this.itemsProcessed = itemsProcessed; }
    
    public Integer getItemsApproved() { return itemsApproved; }
    public void setItemsApproved(Integer itemsApproved) { this.itemsApproved = itemsApproved; }
    
    public Integer getItemsRejected() { return itemsRejected; }
    public void setItemsRejected(Integer itemsRejected) { this.itemsRejected = itemsRejected; }
    
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }
    
    public Long getTriggeredBy() { return triggeredBy; }
    public void setTriggeredBy(Long triggeredBy) { this.triggeredBy = triggeredBy; }
    
    public String getTriggerSource() { return triggerSource; }
    public void setTriggerSource(String triggerSource) { this.triggerSource = triggerSource; }
    
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (executionId == null || executionId.isEmpty()) {
            executionId = "EXEC-" + System.currentTimeMillis();
        }
        if (startTime == null) {
            startTime = LocalDateTime.now();
        }
    }
}
