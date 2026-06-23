package com.bharaterp.model.scm_adv;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "procurement")
public class Procurement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String procurementNumber;
    
    @Column(nullable = false)
    private String procurementType; // DIRECT, INDIRECT, CAPITAL, SERVICE
    
    @Column(nullable = false)
    private Long vendorId;
    
    private String vendorName;
    private String vendorCode;
    
    @Column(nullable = false)
    private String category; // RAW_MATERIAL, PACKAGING, MRO, IT, CONSULTING
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalValue = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal taxAmount = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal grandTotal = BigDecimal.ZERO;
    
    private String currency = "INR";
    
    private String status = "DRAFT"; // DRAFT, REQUESTED, APPROVED, ORDERED, RECEIVED, CLOSED
    
    private String priority = "MEDIUM"; // LOW, MEDIUM, HIGH, URGENT
    
    private LocalDateTime requiredDate;
    private LocalDateTime orderDate;
    private LocalDateTime receivedDate;
    
    private String shippingAddress;
    private String billingAddress;
    
    private String paymentTerms; // NET_30, NET_60, ADVANCE, COD
    
    private String incoterms; // FOB, CIF, EXW, DDP
    
    private String approvedBy;
    private LocalDateTime approvedDate;
    
    private String notes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getProcurementNumber() { return procurementNumber; }
    public void setProcurementNumber(String procurementNumber) { this.procurementNumber = procurementNumber; }
    
    public String getProcurementType() { return procurementType; }
    public void setProcurementType(String procurementType) { this.procurementType = procurementType; }
    
    public Long getVendorId() { return vendorId; }
    public void setVendorId(Long vendorId) { this.vendorId = vendorId; }
    
    public String getVendorName() { return vendorName; }
    public void setVendorName(String vendorName) { this.vendorName = vendorName; }
    
    public String getVendorCode() { return vendorCode; }
    public void setVendorCode(String vendorCode) { this.vendorCode = vendorCode; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public BigDecimal getTotalValue() { return totalValue; }
    public void setTotalValue(BigDecimal totalValue) { this.totalValue = totalValue; }
    
    public BigDecimal getTaxAmount() { return taxAmount; }
    public void setTaxAmount(BigDecimal taxAmount) { this.taxAmount = taxAmount; }
    
    public BigDecimal getGrandTotal() { return grandTotal; }
    public void setGrandTotal(BigDecimal grandTotal) { this.grandTotal = grandTotal; }
    
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    
    public LocalDateTime getRequiredDate() { return requiredDate; }
    public void setRequiredDate(LocalDateTime requiredDate) { this.requiredDate = requiredDate; }
    
    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
    
    public LocalDateTime getReceivedDate() { return receivedDate; }
    public void setReceivedDate(LocalDateTime receivedDate) { this.receivedDate = receivedDate; }
    
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    
    public String getBillingAddress() { return billingAddress; }
    public void setBillingAddress(String billingAddress) { this.billingAddress = billingAddress; }
    
    public String getPaymentTerms() { return paymentTerms; }
    public void setPaymentTerms(String paymentTerms) { this.paymentTerms = paymentTerms; }
    
    public String getIncoterms() { return incoterms; }
    public void setIncoterms(String incoterms) { this.incoterms = incoterms; }
    
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
        if (procurementNumber == null || procurementNumber.isEmpty()) {
            procurementNumber = "PROC-" + System.currentTimeMillis();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
