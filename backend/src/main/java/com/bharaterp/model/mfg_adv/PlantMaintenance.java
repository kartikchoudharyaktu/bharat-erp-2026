package com.bharaterp.model.mfg_adv;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "plant_maintenance")
public class PlantMaintenance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String maintenanceNumber;
    
    @Column(nullable = false)
    private String equipmentName;
    
    private String equipmentCode;
    private String equipmentType; // MACHINE, VEHICLE, FACILITY, TOOL
    
    @Column(nullable = false)
    private String maintenanceType; // PREVENTIVE, PREDICTIVE, CORRECTIVE, EMERGENCY
    
    private String priority; // LOW, MEDIUM, HIGH, CRITICAL
    
    private String status = "SCHEDULED"; // SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    
    private LocalDateTime scheduledDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    private String assignedTo;
    private String teamMembers; // JSON array
    
    private String description;
    private String cause;
    private String resolution;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal estimatedCost = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal actualCost = BigDecimal.ZERO;
    
    private Integer downtimeHours = 0;
    
    private String sparePartsUsed; // JSON array
    
    private String attachments; // JSON array
    
    private String notes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMaintenanceNumber() { return maintenanceNumber; }
    public void setMaintenanceNumber(String maintenanceNumber) { this.maintenanceNumber = maintenanceNumber; }
    
    public String getEquipmentName() { return equipmentName; }
    public void setEquipmentName(String equipmentName) { this.equipmentName = equipmentName; }
    
    public String getEquipmentCode() { return equipmentCode; }
    public void setEquipmentCode(String equipmentCode) { this.equipmentCode = equipmentCode; }
    
    public String getEquipmentType() { return equipmentType; }
    public void setEquipmentType(String equipmentType) { this.equipmentType = equipmentType; }
    
    public String getMaintenanceType() { return maintenanceType; }
    public void setMaintenanceType(String maintenanceType) { this.maintenanceType = maintenanceType; }
    
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getScheduledDate() { return scheduledDate; }
    public void setScheduledDate(LocalDateTime scheduledDate) { this.scheduledDate = scheduledDate; }
    
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    
    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
    
    public String getTeamMembers() { return teamMembers; }
    public void setTeamMembers(String teamMembers) { this.teamMembers = teamMembers; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getCause() { return cause; }
    public void setCause(String cause) { this.cause = cause; }
    
    public String getResolution() { return resolution; }
    public void setResolution(String resolution) { this.resolution = resolution; }
    
    public BigDecimal getEstimatedCost() { return estimatedCost; }
    public void setEstimatedCost(BigDecimal estimatedCost) { this.estimatedCost = estimatedCost; }
    
    public BigDecimal getActualCost() { return actualCost; }
    public void setActualCost(BigDecimal actualCost) { this.actualCost = actualCost; }
    
    public Integer getDowntimeHours() { return downtimeHours; }
    public void setDowntimeHours(Integer downtimeHours) { this.downtimeHours = downtimeHours; }
    
    public String getSparePartsUsed() { return sparePartsUsed; }
    public void setSparePartsUsed(String sparePartsUsed) { this.sparePartsUsed = sparePartsUsed; }
    
    public String getAttachments() { return attachments; }
    public void setAttachments(String attachments) { this.attachments = attachments; }
    
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
        if (maintenanceNumber == null || maintenanceNumber.isEmpty()) {
            maintenanceNumber = "MTN-" + System.currentTimeMillis();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
