package com.bharaterp.model.hr_adv;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "recruitment")
public class Recruitment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String jobTitle;
    
    @Column(nullable = false)
    private String department;
    
    private String jobType; // FULL_TIME, PART_TIME, CONTRACT, INTERNSHIP
    
    private String experienceLevel; // FRESH, 1-3, 3-5, 5-8, 8+
    
    private String location;
    private String remoteType; // ONSITE, REMOTE, HYBRID
    
    private String skillsRequired; // JSON array
    
    private String qualifications; // JSON array
    
    @Column(precision = 19, scale = 2)
    private BigDecimal salaryMin = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal salaryMax = BigDecimal.ZERO;
    
    private String status = "OPEN"; // OPEN, CLOSED, ON_HOLD, FILLED
    
    private Integer applications = 0;
    private Integer shortlisted = 0;
    private Integer interviewed = 0;
    private Integer offered = 0;
    private Integer hired = 0;
    
    private String jobDescription;
    
    private String hiringManager;
    private String hrContact;
    
    private LocalDateTime postingDate;
    private LocalDateTime closingDate;
    
    private String notes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    
    public String getJobType() { return jobType; }
    public void setJobType(String jobType) { this.jobType = jobType; }
    
    public String getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(String experienceLevel) { this.experienceLevel = experienceLevel; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getRemoteType() { return remoteType; }
    public void setRemoteType(String remoteType) { this.remoteType = remoteType; }
    
    public String getSkillsRequired() { return skillsRequired; }
    public void setSkillsRequired(String skillsRequired) { this.skillsRequired = skillsRequired; }
    
    public String getQualifications() { return qualifications; }
    public void setQualifications(String qualifications) { this.qualifications = qualifications; }
    
    public BigDecimal getSalaryMin() { return salaryMin; }
    public void setSalaryMin(BigDecimal salaryMin) { this.salaryMin = salaryMin; }
    
    public BigDecimal getSalaryMax() { return salaryMax; }
    public void setSalaryMax(BigDecimal salaryMax) { this.salaryMax = salaryMax; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Integer getApplications() { return applications; }
    public void setApplications(Integer applications) { this.applications = applications; }
    
    public Integer getShortlisted() { return shortlisted; }
    public void setShortlisted(Integer shortlisted) { this.shortlisted = shortlisted; }
    
    public Integer getInterviewed() { return interviewed; }
    public void setInterviewed(Integer interviewed) { this.interviewed = interviewed; }
    
    public Integer getOffered() { return offered; }
    public void setOffered(Integer offered) { this.offered = offered; }
    
    public Integer getHired() { return hired; }
    public void setHired(Integer hired) { this.hired = hired; }
    
    public String getJobDescription() { return jobDescription; }
    public void setJobDescription(String jobDescription) { this.jobDescription = jobDescription; }
    
    public String getHiringManager() { return hiringManager; }
    public void setHiringManager(String hiringManager) { this.hiringManager = hiringManager; }
    
    public String getHrContact() { return hrContact; }
    public void setHrContact(String hrContact) { this.hrContact = hrContact; }
    
    public LocalDateTime getPostingDate() { return postingDate; }
    public void setPostingDate(LocalDateTime postingDate) { this.postingDate = postingDate; }
    
    public LocalDateTime getClosingDate() { return closingDate; }
    public void setClosingDate(LocalDateTime closingDate) { this.closingDate = closingDate; }
    
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
        postingDate = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
