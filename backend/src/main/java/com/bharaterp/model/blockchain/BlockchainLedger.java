package com.bharaterp.model.blockchain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "blockchain_ledger")
public class BlockchainLedger {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String blockHash;
    
    @Column(nullable = false)
    private String previousBlockHash;
    
    private Long blockNumber;
    
    @Column(nullable = false)
    private String transactionType; // FINANCIAL, INVENTORY, HR, SALES, AUDIT
    
    private String transactionId;
    
    private String sender;
    private String receiver;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal amount = BigDecimal.ZERO;
    
    private String currency = "INR";
    
    @Column(length = 4000)
    private String data; // JSON
    
    private String digitalSignature;
    
    private String consensusMethod; // PoW, PoS, PBFT
    
    private String status = "PENDING"; // PENDING, CONFIRMED, REJECTED
    
    private Integer confirmations = 0;
    
    private String validationHash;
    private String validatedBy;
    private LocalDateTime validationDate;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getBlockHash() { return blockHash; }
    public void setBlockHash(String blockHash) { this.blockHash = blockHash; }
    
    public String getPreviousBlockHash() { return previousBlockHash; }
    public void setPreviousBlockHash(String previousBlockHash) { this.previousBlockHash = previousBlockHash; }
    
    public Long getBlockNumber() { return blockNumber; }
    public void setBlockNumber(Long blockNumber) { this.blockNumber = blockNumber; }
    
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    
    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    
    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }
    
    public String getReceiver() { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }
    
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
    public String getData() { return data; }
    public void setData(String data) { this.data = data; }
    
    public String getDigitalSignature() { return digitalSignature; }
    public void setDigitalSignature(String digitalSignature) { this.digitalSignature = digitalSignature; }
    
    public String getConsensusMethod() { return consensusMethod; }
    public void setConsensusMethod(String consensusMethod) { this.consensusMethod = consensusMethod; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Integer getConfirmations() { return confirmations; }
    public void setConfirmations(Integer confirmations) { this.confirmations = confirmations; }
    
    public String getValidationHash() { return validationHash; }
    public void setValidationHash(String validationHash) { this.validationHash = validationHash; }
    
    public String getValidatedBy() { return validatedBy; }
    public void setValidatedBy(String validatedBy) { this.validatedBy = validatedBy; }
    
    public LocalDateTime getValidationDate() { return validationDate; }
    public void setValidationDate(LocalDateTime validationDate) { this.validationDate = validationDate; }
    
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
        if (blockHash == null || blockHash.isEmpty()) {
            blockHash = "0x" + Long.toHexString(System.currentTimeMillis()) + 
                        Long.toHexString(System.nanoTime());
        }
        if (blockNumber == null) {
            blockNumber = System.currentTimeMillis();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
