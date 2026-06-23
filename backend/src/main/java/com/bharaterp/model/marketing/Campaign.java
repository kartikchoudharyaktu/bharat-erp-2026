package com.bharaterp.model.marketing;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "campaigns")
public class Campaign {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String campaignName;
    
    private String campaignType; // EMAIL, SOCIAL, SEO, PAID_ADS, CONTENT
    
    private String targetAudience;
    private String targetSegment;
    
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal budget = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal spent = BigDecimal.ZERO;
    
    private Integer impressions = 0;
    private Integer clicks = 0;
    private Integer leads = 0;
    private Integer conversions = 0;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal revenueGenerated = BigDecimal.ZERO;
    
    private Double conversionRate = 0.0;
    private Double roi = 0.0;
    
    private String status = "PLANNED"; // PLANNED, ACTIVE, PAUSED, COMPLETED, CANCELLED
    
    private String description;
    private String notes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getCampaignName() { return campaignName; }
    public void setCampaignName(String campaignName) { this.campaignName = campaignName; }
    
    public String getCampaignType() { return campaignType; }
    public void setCampaignType(String campaignType) { this.campaignType = campaignType; }
    
    public String getTargetAudience() { return targetAudience; }
    public void setTargetAudience(String targetAudience) { this.targetAudience = targetAudience; }
    
    public String getTargetSegment() { return targetSegment; }
    public void setTargetSegment(String targetSegment) { this.targetSegment = targetSegment; }
    
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    
    public BigDecimal getBudget() { return budget; }
    public void setBudget(BigDecimal budget) { this.budget = budget; }
    
    public BigDecimal getSpent() { return spent; }
    public void setSpent(BigDecimal spent) { this.spent = spent; }
    
    public Integer getImpressions() { return impressions; }
    public void setImpressions(Integer impressions) { this.impressions = impressions; }
    
    public Integer getClicks() { return clicks; }
    public void setClicks(Integer clicks) { this.clicks = clicks; }
    
    public Integer getLeads() { return leads; }
    public void setLeads(Integer leads) { this.leads = leads; }
    
    public Integer getConversions() { return conversions; }
    public void setConversions(Integer conversions) { this.conversions = conversions; }
    
    public BigDecimal getRevenueGenerated() { return revenueGenerated; }
    public void setRevenueGenerated(BigDecimal revenueGenerated) { this.revenueGenerated = revenueGenerated; }
    
    public Double getConversionRate() { return conversionRate; }
    public void setConversionRate(Double conversionRate) { this.conversionRate = conversionRate; }
    
    public Double getRoi() { return roi; }
    public void setRoi(Double roi) { this.roi = roi; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
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
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
