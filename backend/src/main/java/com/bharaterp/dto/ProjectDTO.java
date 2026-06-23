package com.bharaterp.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectDTO {
    private Long id; private String projectName; private String projectCode; private String description;
    private LocalDateTime startDate; private LocalDateTime endDate; private String status;
    private Double budget; private Double cost; private Long clientId; private String clientName;
    private String priority; private String category; private List<TaskDTO> tasks;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getProjectCode() { return projectCode; }
    public void setProjectCode(String projectCode) { this.projectCode = projectCode; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
    public Long getClientId() { return clientId; }
    public void setClientId(Long clientId) { this.clientId = clientId; }
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public List<TaskDTO> getTasks() { return tasks; }
    public void setTasks(List<TaskDTO> tasks) { this.tasks = tasks; }
}
