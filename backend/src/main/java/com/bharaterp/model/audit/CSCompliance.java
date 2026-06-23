package com.bharaterp.model.audit;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cs_compliance")
public class CSCompliance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String complianceType; // BOARD_MEETING, SHAREHOLDER_MEETING, ROC_FILING, ANNUAL_REPORT
    
    @Column(nullable = false)
    private String companyName;
    private String companyCode;
    private String cinNumber; // Company Identification Number
    
    private String meetingType; // BOARD, AGM, EGM
    private String meetingDate;
    private String meetingPlace;
    private String meetingMinutes;
    
    private String resolutionType; // ORDINARY, SPECIAL, BOARD
    private String resolutionDetails;
    private String resolutionDate;
    
    private String filingType; // ROC, MCA, STOCK_EXCHANGE
    private String filingDetails;
    private String filingDate;
    
    private String csName;
    private String csMembershipNumber;
    private String csFirmName;
    
    private String status = "PENDING"; // PENDING, COMPLETED, FILED, REJECTED
    
    private String remarks;
    private String attachments; // JSON
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getComplianceType() { return complianceType; }
    public void setComplianceType(String complianceType) { this.complianceType = complianceType; }
    
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    
    public String getCompanyCode() { return companyCode; }
    public void setCompanyCode(String companyCode) { this.companyCode = companyCode; }
    
    public String getCinNumber() { return cinNumber; }
    public void setCinNumber(String cinNumber) { this.cinNumber = cinNumber; }
    
    public String getMeetingType() { return meetingType; }
    public void setMeetingType(String meetingType) { this.meetingType = meetingType; }
    
    public String getMeetingDate() { return meetingDate; }
    public void setMeetingDate(String meetingDate) { this.meetingDate = meetingDate; }
    
    public String getMeetingPlace() { return meetingPlace; }
    public void setMeetingPlace(String meetingPlace) { this.meetingPlace = meetingPlace; }
    
    public String getMeetingMinutes() { return meetingMinutes; }
    public void setMeetingMinutes(String meetingMinutes) { this.meetingMinutes = meetingMinutes; }
    
    public String getResolutionType() { return resolutionType; }
    public void setResolutionType(String resolutionType) { this.resolutionType = resolutionType; }
    
    public String getResolutionDetails() { return resolutionDetails; }
    public void setResolutionDetails(String resolutionDetails) { this.resolutionDetails = resolutionDetails; }
    
    public String getResolutionDate() { return resolutionDate; }
    public void setResolutionDate(String resolutionDate) { this.resolutionDate = resolutionDate; }
    
    public String getFilingType() { return filingType; }
    public void setFilingType(String filingType) { this.filingType = filingType; }
    
    public String getFilingDetails() { return filingDetails; }
    public void setFilingDetails(String filingDetails) { this.filingDetails = filingDetails; }
    
    public String getFilingDate() { return filingDate; }
    public void setFilingDate(String filingDate) { this.filingDate = filingDate; }
    
    public String getCsName() { return csName; }
    public void setCsName(String csName) { this.csName = csName; }
    
    public String getCsMembershipNumber() { return csMembershipNumber; }
    public void setCsMembershipNumber(String csMembershipNumber) { this.csMembershipNumber = csMembershipNumber; }
    
    public String getCsFirmName() { return csFirmName; }
    public void setCsFirmName(String csFirmName) { this.csFirmName = csFirmName; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public String getAttachments() { return attachments; }
    public void setAttachments(String attachments) { this.attachments = attachments; }
    
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
