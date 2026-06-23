package com.bharaterp.model.hr_adv;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "onboarding")
public class Onboarding {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long employeeId;
    
    private String employeeName;
    private String employeeCode;
    
    @Column(nullable = false)
    private String onboardingStatus; // NOT_STARTED, IN_PROGRESS, COMPLETED, DELAYED
    
    private String welcomeEmailSent;
    private String documentsSubmitted;
    private String documentsVerified;
    private String orientationCompleted;
    private String trainingCompleted;
    private String equipmentAllocated;
    private String accessGranted;
    private String mentorAssigned;
    private String buddyAssigned;
    
    private String orientationDate;
    private String trainingStartDate;
    private String trainingEndDate;
    private String joiningDate;
    
    private String checklist; // JSON array
    
    private String notes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    
    public String getEmployeeCode() { return employeeCode; }
    public void setEmployeeCode(String employeeCode) { this.employeeCode = employeeCode; }
    
    public String getOnboardingStatus() { return onboardingStatus; }
    public void setOnboardingStatus(String onboardingStatus) { this.onboardingStatus = onboardingStatus; }
    
    public String getWelcomeEmailSent() { return welcomeEmailSent; }
    public void setWelcomeEmailSent(String welcomeEmailSent) { this.welcomeEmailSent = welcomeEmailSent; }
    
    public String getDocumentsSubmitted() { return documentsSubmitted; }
    public void setDocumentsSubmitted(String documentsSubmitted) { this.documentsSubmitted = documentsSubmitted; }
    
    public String getDocumentsVerified() { return documentsVerified; }
    public void setDocumentsVerified(String documentsVerified) { this.documentsVerified = documentsVerified; }
    
    public String getOrientationCompleted() { return orientationCompleted; }
    public void setOrientationCompleted(String orientationCompleted) { this.orientationCompleted = orientationCompleted; }
    
    public String getTrainingCompleted() { return trainingCompleted; }
    public void setTrainingCompleted(String trainingCompleted) { this.trainingCompleted = trainingCompleted; }
    
    public String getEquipmentAllocated() { return equipmentAllocated; }
    public void setEquipmentAllocated(String equipmentAllocated) { this.equipmentAllocated = equipmentAllocated; }
    
    public String getAccessGranted() { return accessGranted; }
    public void setAccessGranted(String accessGranted) { this.accessGranted = accessGranted; }
    
    public String getMentorAssigned() { return mentorAssigned; }
    public void setMentorAssigned(String mentorAssigned) { this.mentorAssigned = mentorAssigned; }
    
    public String getBuddyAssigned() { return buddyAssigned; }
    public void setBuddyAssigned(String buddyAssigned) { this.buddyAssigned = buddyAssigned; }
    
    public String getOrientationDate() { return orientationDate; }
    public void setOrientationDate(String orientationDate) { this.orientationDate = orientationDate; }
    
    public String getTrainingStartDate() { return trainingStartDate; }
    public void setTrainingStartDate(String trainingStartDate) { this.trainingStartDate = trainingStartDate; }
    
    public String getTrainingEndDate() { return trainingEndDate; }
    public void setTrainingEndDate(String trainingEndDate) { this.trainingEndDate = trainingEndDate; }
    
    public String getJoiningDate() { return joiningDate; }
    public void setJoiningDate(String joiningDate) { this.joiningDate = joiningDate; }
    
    public String getChecklist() { return checklist; }
    public void setChecklist(String checklist) { this.checklist = checklist; }
    
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
        onboardingStatus = "NOT_STARTED";
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
