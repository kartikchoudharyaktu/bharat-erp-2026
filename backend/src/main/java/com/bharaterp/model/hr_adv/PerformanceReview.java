package com.bharaterp.model.hr_adv;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "performance_reviews")
public class PerformanceReview {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long employeeId;
    
    private String employeeName;
    private String employeeCode;
    
    @Column(nullable = false)
    private String reviewPeriod; // QUARTERLY, HALF_YEARLY, ANNUAL
    
    private String reviewYear;
    private String quarter;
    
    @Column(nullable = false)
    private String reviewer;
    
    private String reviewDate;
    
    private Integer technicalSkills = 0;
    private Integer communication = 0;
    private Integer teamwork = 0;
    private Integer problemSolving = 0;
    private Integer leadership = 0;
    private Integer adaptability = 0;
    private Integer productivity = 0;
    private Integer qualityOfWork = 0;
    
    private Integer totalScore = 0;
    private Double averageScore = 0.0;
    
    private String rating; // OUTSTANDING, EXCELLENT, GOOD, SATISFACTORY, NEEDS_IMPROVEMENT
    
    private String strengths;
    private String weaknesses;
    private String achievements;
    private String goals;
    private String areasForImprovement;
    
    private String status = "DRAFT"; // DRAFT, SUBMITTED, REVIEWED, COMPLETED
    
    private String reviewerComments;
    private String employeeComments;
    
    private String nextReviewDate;
    
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
    
    public String getReviewPeriod() { return reviewPeriod; }
    public void setReviewPeriod(String reviewPeriod) { this.reviewPeriod = reviewPeriod; }
    
    public String getReviewYear() { return reviewYear; }
    public void setReviewYear(String reviewYear) { this.reviewYear = reviewYear; }
    
    public String getQuarter() { return quarter; }
    public void setQuarter(String quarter) { this.quarter = quarter; }
    
    public String getReviewer() { return reviewer; }
    public void setReviewer(String reviewer) { this.reviewer = reviewer; }
    
    public String getReviewDate() { return reviewDate; }
    public void setReviewDate(String reviewDate) { this.reviewDate = reviewDate; }
    
    public Integer getTechnicalSkills() { return technicalSkills; }
    public void setTechnicalSkills(Integer technicalSkills) { this.technicalSkills = technicalSkills; }
    
    public Integer getCommunication() { return communication; }
    public void setCommunication(Integer communication) { this.communication = communication; }
    
    public Integer getTeamwork() { return teamwork; }
    public void setTeamwork(Integer teamwork) { this.teamwork = teamwork; }
    
    public Integer getProblemSolving() { return problemSolving; }
    public void setProblemSolving(Integer problemSolving) { this.problemSolving = problemSolving; }
    
    public Integer getLeadership() { return leadership; }
    public void setLeadership(Integer leadership) { this.leadership = leadership; }
    
    public Integer getAdaptability() { return adaptability; }
    public void setAdaptability(Integer adaptability) { this.adaptability = adaptability; }
    
    public Integer getProductivity() { return productivity; }
    public void setProductivity(Integer productivity) { this.productivity = productivity; }
    
    public Integer getQualityOfWork() { return qualityOfWork; }
    public void setQualityOfWork(Integer qualityOfWork) { this.qualityOfWork = qualityOfWork; }
    
    public Integer getTotalScore() { return totalScore; }
    public void setTotalScore(Integer totalScore) { this.totalScore = totalScore; }
    
    public Double getAverageScore() { return averageScore; }
    public void setAverageScore(Double averageScore) { this.averageScore = averageScore; }
    
    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
    
    public String getStrengths() { return strengths; }
    public void setStrengths(String strengths) { this.strengths = strengths; }
    
    public String getWeaknesses() { return weaknesses; }
    public void setWeaknesses(String weaknesses) { this.weaknesses = weaknesses; }
    
    public String getAchievements() { return achievements; }
    public void setAchievements(String achievements) { this.achievements = achievements; }
    
    public String getGoals() { return goals; }
    public void setGoals(String goals) { this.goals = goals; }
    
    public String getAreasForImprovement() { return areasForImprovement; }
    public void setAreasForImprovement(String areasForImprovement) { this.areasForImprovement = areasForImprovement; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getReviewerComments() { return reviewerComments; }
    public void setReviewerComments(String reviewerComments) { this.reviewerComments = reviewerComments; }
    
    public String getEmployeeComments() { return employeeComments; }
    public void setEmployeeComments(String employeeComments) { this.employeeComments = employeeComments; }
    
    public String getNextReviewDate() { return nextReviewDate; }
    public void setNextReviewDate(String nextReviewDate) { this.nextReviewDate = nextReviewDate; }
    
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
