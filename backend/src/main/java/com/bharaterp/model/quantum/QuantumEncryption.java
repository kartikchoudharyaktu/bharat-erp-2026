package com.bharaterp.model.quantum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "quantum_encryption")
public class QuantumEncryption {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String encryptionKey;
    
    @Column(nullable = false)
    private String keyType; // AES-256, RSA-4096, ECC, POST_QUANTUM
    
    private String algorithm; // Kyber, Dilithium, Falcon, SPHINCS+
    
    private String keySize; // 256, 512, 1024, 2048, 4096
    
    private String quantumResistance; // HIGH, MEDIUM, LOW
    
    private String purpose; // DATA, COMMUNICATION, STORAGE, AUTH
    
    private String status = "ACTIVE"; // ACTIVE, ROTATED, REVOKED
    
    private String rotationSchedule; // CRON
    
    private String generatedBy;
    private LocalDateTime generationDate;
    private LocalDateTime expiryDate;
    
    private String notes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getEncryptionKey() { return encryptionKey; }
    public void setEncryptionKey(String encryptionKey) { this.encryptionKey = encryptionKey; }
    
    public String getKeyType() { return keyType; }
    public void setKeyType(String keyType) { this.keyType = keyType; }
    
    public String getAlgorithm() { return algorithm; }
    public void setAlgorithm(String algorithm) { this.algorithm = algorithm; }
    
    public String getKeySize() { return keySize; }
    public void setKeySize(String keySize) { this.keySize = keySize; }
    
    public String getQuantumResistance() { return quantumResistance; }
    public void setQuantumResistance(String quantumResistance) { this.quantumResistance = quantumResistance; }
    
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getRotationSchedule() { return rotationSchedule; }
    public void setRotationSchedule(String rotationSchedule) { this.rotationSchedule = rotationSchedule; }
    
    public String getGeneratedBy() { return generatedBy; }
    public void setGeneratedBy(String generatedBy) { this.generatedBy = generatedBy; }
    
    public LocalDateTime getGenerationDate() { return generationDate; }
    public void setGenerationDate(LocalDateTime generationDate) { this.generationDate = generationDate; }
    
    public LocalDateTime getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
    
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
        if (encryptionKey == null || encryptionKey.isEmpty()) {
            encryptionKey = "Q" + Long.toHexString(System.currentTimeMillis()) + 
                            Long.toHexString(System.nanoTime());
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
