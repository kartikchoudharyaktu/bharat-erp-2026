package com.bharaterp.model.finance_adv;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "treasury_management")
public class TreasuryManagement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String treasuryType; // CASH, INVESTMENT, LOAN, DERIVATIVE, FOREIGN_EXCHANGE
    
    private String instrumentName;
    private String instrumentCode;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal amount = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal interestRate = BigDecimal.ZERO;
    
    private String currency = "INR";
    
    private LocalDateTime startDate;
    private LocalDateTime maturityDate;
    
    private String counterparty;
    private String counterpartyType; // BANK, FINANCIAL_INSTITUTION, CORPORATE
    
    private String riskLevel; // LOW, MEDIUM, HIGH
    
    @Column(precision = 19, scale = 2)
    private BigDecimal marketValue = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal realizedGain = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal unrealizedGain = BigDecimal.ZERO;
    
    private String status = "ACTIVE"; // ACTIVE, MATURED, CANCELLED
    
    private String purpose; // LIQUIDITY, HEDGING, INVESTMENT, FUNDING
    
    private String notes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTreasuryType() { return treasuryType; }
    public void setTreasuryType(String treasuryType) { this.treasuryType = treasuryType; }
    
    public String getInstrumentName() { return instrumentName; }
    public void setInstrumentName(String instrumentName) { this.instrumentName = instrumentName; }
    
    public String getInstrumentCode() { return instrumentCode; }
    public void setInstrumentCode(String instrumentCode) { this.instrumentCode = instrumentCode; }
    
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    
    public BigDecimal getInterestRate() { return interestRate; }
    public void setInterestRate(BigDecimal interestRate) { this.interestRate = interestRate; }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    
    public LocalDateTime getMaturityDate() { return maturityDate; }
    public void setMaturityDate(LocalDateTime maturityDate) { this.maturityDate = maturityDate; }
    
    public String getCounterparty() { return counterparty; }
    public void setCounterparty(String counterparty) { this.counterparty = counterparty; }
    
    public String getCounterpartyType() { return counterpartyType; }
    public void setCounterpartyType(String counterpartyType) { this.counterpartyType = counterpartyType; }
    
    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }
    
    public BigDecimal getMarketValue() { return marketValue; }
    public void setMarketValue(BigDecimal marketValue) { this.marketValue = marketValue; }
    
    public BigDecimal getRealizedGain() { return realizedGain; }
    public void setRealizedGain(BigDecimal realizedGain) { this.realizedGain = realizedGain; }
    
    public BigDecimal getUnrealizedGain() { return unrealizedGain; }
    public void setUnrealizedGain(BigDecimal unrealizedGain) { this.unrealizedGain = unrealizedGain; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    
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
