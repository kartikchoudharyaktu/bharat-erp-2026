package com.bharaterp.model.enhancement;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "bank_reconciliation_ai")
public class BankReconciliationAI {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long bankStatementId;
    
    @Column(nullable = false)
    private Long transactionId;
    
    @Column(nullable = false)
    private String matchType; // EXACT, NEAR_EXACT, SUGGESTED, MANUAL
    
    @Column(precision = 19, scale = 2)
    private BigDecimal bankAmount = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal systemAmount = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal difference = BigDecimal.ZERO;
    
    private Double matchScore = 0.0; // 0-100
    
    private String status = "PENDING"; // PENDING, MATCHED, UNMATCHED, RESOLVED
    
    private String suggestions; // JSON suggestions
    
    private String resolvedBy;
    private LocalDateTime resolvedAt;
    private String resolutionNotes;
    
    private String confidence = "0.0";
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getBankStatementId() { return bankStatementId; }
    public void setBankStatementId(Long bankStatementId) { this.bankStatementId = bankStatementId; }
    
    public Long getTransactionId() { return transactionId; }
    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; }
    
    public String getMatchType() { return matchType; }
    public void setMatchType(String matchType) { this.matchType = matchType; }
    
    public BigDecimal getBankAmount() { return bankAmount; }
    public void setBankAmount(BigDecimal bankAmount) { this.bankAmount = bankAmount; }
    
    public BigDecimal getSystemAmount() { return systemAmount; }
    public void setSystemAmount(BigDecimal systemAmount) { this.systemAmount = systemAmount; }
    
    public BigDecimal getDifference() { return difference; }
    public void setDifference(BigDecimal difference) { this.difference = difference; }
    
    public Double getMatchScore() { return matchScore; }
    public void setMatchScore(Double matchScore) { this.matchScore = matchScore; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getSuggestions() { return suggestions; }
    public void setSuggestions(String suggestions) { this.suggestions = suggestions; }
    
    public String getResolvedBy() { return resolvedBy; }
    public void setResolvedBy(String resolvedBy) { this.resolvedBy = resolvedBy; }
    
    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }
    
    public String getResolutionNotes() { return resolutionNotes; }
    public void setResolutionNotes(String resolutionNotes) { this.resolutionNotes = resolutionNotes; }
    
    public String getConfidence() { return confidence; }
    public void setConfidence(String confidence) { this.confidence = confidence; }
    
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
