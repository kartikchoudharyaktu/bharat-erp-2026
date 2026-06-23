package com.bharaterp.model.ai;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ai_chats")
public class AIChat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long userId;
    
    private String sessionId;
    
    @Column(nullable = false, length = 2000)
    private String userMessage;
    
    @Column(nullable = false, length = 4000)
    private String aiResponse;
    
    private String intent; // QUERY, ACTION, REPORT, HELP
    
    private Double confidence = 0.0;
    
    private String context;
    private String module; // FINANCE, HR, INVENTORY, etc.
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    
    public String getUserMessage() { return userMessage; }
    public void setUserMessage(String userMessage) { this.userMessage = userMessage; }
    
    public String getAiResponse() { return aiResponse; }
    public void setAiResponse(String aiResponse) { this.aiResponse = aiResponse; }
    
    public String getIntent() { return intent; }
    public void setIntent(String intent) { this.intent = intent; }
    
    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }
    
    public String getContext() { return context; }
    public void setContext(String context) { this.context = context; }
    
    public String getModule() { return module; }
    public void setModule(String module) { this.module = module; }
    
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
