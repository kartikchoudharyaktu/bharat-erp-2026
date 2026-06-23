package com.bharaterp.model.finance_adv;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "intercompany_transactions")
public class IntercompanyTransaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String transactionNumber;
    
    @Column(nullable = false)
    private Long sourceCompanyId;
    
    private String sourceCompanyName;
    
    @Column(nullable = false)
    private Long targetCompanyId;
    
    private String targetCompanyName;
    
    private String transactionType; // SALE, PURCHASE, FUND_TRANSFER, SERVICE, LOAN
    
    @Column(precision = 19, scale = 2)
    private BigDecimal amount = BigDecimal.ZERO;
    
    private String currency = "INR";
    
    @Column(precision = 19, scale = 2)
    private BigDecimal exchangeRate = BigDecimal.ONE;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal amountInSourceCurrency = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal amountInTargetCurrency = BigDecimal.ZERO;
    
    private String description;
    
    private String status = "PENDING"; // PENDING, APPROVED, REJECTED, SETTLED
    
    private String settlementMethod; // CASH, ADJUSTMENT, JOURNAL_ENTRY
    
    private LocalDateTime transactionDate;
    private LocalDateTime settlementDate;
    
    private String approvedBy;
    private LocalDateTime approvedDate;
    
    private String notes;
    
    private Long companyId; // Master company ID
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTransactionNumber() { return transactionNumber; }
    public void setTransactionNumber(String transactionNumber) { this.transactionNumber = transactionNumber; }
    
    public Long getSourceCompanyId() { return sourceCompanyId; }
    public void setSourceCompanyId(Long sourceCompanyId) { this.sourceCompanyId = sourceCompanyId; }
    
    public String getSourceCompanyName() { return sourceCompanyName; }
    public void setSourceCompanyName(String sourceCompanyName) { this.sourceCompanyName = sourceCompanyName; }
    
    public Long getTargetCompanyId() { return targetCompanyId; }
    public void setTargetCompanyId(Long targetCompanyId) { this.targetCompanyId = targetCompanyId; }
    
    public String getTargetCompanyName() { return targetCompanyName; }
    public void setTargetCompanyName(String targetCompanyName) { this.targetCompanyName = targetCompanyName; }
    
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
    public BigDecimal getExchangeRate() { return exchangeRate; }
    public void setExchangeRate(BigDecimal exchangeRate) { this.exchangeRate = exchangeRate; }
    
    public BigDecimal getAmountInSourceCurrency() { return amountInSourceCurrency; }
    public void setAmountInSourceCurrency(BigDecimal amountInSourceCurrency) { this.amountInSourceCurrency = amountInSourceCurrency; }
    
    public BigDecimal getAmountInTargetCurrency() { return amountInTargetCurrency; }
    public void setAmountInTargetCurrency(BigDecimal amountInTargetCurrency) { this.amountInTargetCurrency = amountInTargetCurrency; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getSettlementMethod() { return settlementMethod; }
    public void setSettlementMethod(String settlementMethod) { this.settlementMethod = settlementMethod; }
    
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
    
    public LocalDateTime getSettlementDate() { return settlementDate; }
    public void setSettlementDate(LocalDateTime settlementDate) { this.settlementDate = settlementDate; }
    
    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
    
    public LocalDateTime getApprovedDate() { return approvedDate; }
    public void setApprovedDate(LocalDateTime approvedDate) { this.approvedDate = approvedDate; }
    
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
        if (transactionNumber == null || transactionNumber.isEmpty()) {
            transactionNumber = "ICT-" + System.currentTimeMillis();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
