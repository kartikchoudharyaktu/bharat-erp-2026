package com.bharaterp.model.sales_adv;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "marketing_automation")
public class MarketingAutomation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String campaignName;
    
    private String campaignType; // EMAIL, SMS, SOCIAL, PUSH, WHATSAPP
    
    private String audience; // JSON array of segments
    
    private String content;
    private String subjectLine;
    private String senderName;
    private String senderEmail;
    
    private String schedule;
    private LocalDateTime scheduledTime;
    private LocalDateTime sentTime;
    
    private Integer sentCount = 0;
    private Integer deliveredCount = 0;
    private Integer openedCount = 0;
    private Integer clickedCount = 0;
    private Integer convertedCount = 0;
    
    private Double openRate = 0.0;
    private Double clickRate = 0.0;
    private Double conversionRate = 0.0;
    
    private String status = "DRAFT"; // DRAFT, SCHEDULED, SENT, CANCELLED
    
    private String personalization; // JSON
    
    private String template;
    private String attachments; // JSON array
    
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
    
    public String getAudience() { return audience; }
    public void setAudience(String audience) { this.audience = audience; }
    
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    
    public String getSubjectLine() { return subjectLine; }
    public void setSubjectLine(String subjectLine) { this.subjectLine = subjectLine; }
    
    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }
    
    public String getSenderEmail() { return senderEmail; }
    public void setSenderEmail(String senderEmail) { this.senderEmail = senderEmail; }
    
    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    
    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }
    
    public LocalDateTime getSentTime() { return sentTime; }
    public void setSentTime(LocalDateTime sentTime) { this.sentTime = sentTime; }
    
    public Integer getSentCount() { return sentCount; }
    public void setSentCount(Integer sentCount) { this.sentCount = sentCount; }
    
    public Integer getDeliveredCount() { return deliveredCount; }
    public void setDeliveredCount(Integer deliveredCount) { this.deliveredCount = deliveredCount; }
    
    public Integer getOpenedCount() { return openedCount; }
    public void setOpenedCount(Integer openedCount) { this.openedCount = openedCount; }
    
    public Integer getClickedCount() { return clickedCount; }
    public void setClickedCount(Integer clickedCount) { this.clickedCount = clickedCount; }
    
    public Integer getConvertedCount() { return convertedCount; }
    public void setConvertedCount(Integer convertedCount) { this.convertedCount = convertedCount; }
    
    public Double getOpenRate() { return openRate; }
    public void setOpenRate(Double openRate) { this.openRate = openRate; }
    
    public Double getClickRate() { return clickRate; }
    public void setClickRate(Double clickRate) { this.clickRate = clickRate; }
    
    public Double getConversionRate() { return conversionRate; }
    public void setConversionRate(Double conversionRate) { this.conversionRate = conversionRate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getPersonalization() { return personalization; }
    public void setPersonalization(String personalization) { this.personalization = personalization; }
    
    public String getTemplate() { return template; }
    public void setTemplate(String template) { this.template = template; }
    
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
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
