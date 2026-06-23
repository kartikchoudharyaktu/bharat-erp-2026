package com.bharaterp.model.scm_adv;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "return_logistics")
public class ReturnLogistics {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String returnNumber;
    
    @Column(nullable = false)
    private String returnType; // CUSTOMER_RETURN, VENDOR_RETURN, QUALITY_REJECT, EXCHANGE
    
    @Column(nullable = false)
    private Long customerId; // or vendorId
    
    private String customerName;
    private String customerEmail;
    
    @Column(nullable = false)
    private String reason; // DAMAGED, WRONG_ITEM, DEFECTIVE, NOT_NEEDED, QUALITY_ISSUE
    
    private String returnStatus = "REQUESTED"; // REQUESTED, APPROVED, PICKED, RECEIVED, INSPECTED, REFUNDED, CLOSED
    
    @Column(precision = 19, scale = 2)
    private BigDecimal refundAmount = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal restockingFee = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal netRefund = BigDecimal.ZERO;
    
    private String refundMethod; // ORIGINAL_PAYMENT, STORE_CREDIT, BANK_TRANSFER
    
    private String shippingMethod;
    private String trackingNumber;
    
    private String approvalStatus = "PENDING"; // PENDING, APPROVED, REJECTED
    private String approvedBy;
    private LocalDateTime approvedDate;
    
    private String notes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getReturnNumber() { return returnNumber; }
    public void setReturnNumber(String returnNumber) { this.returnNumber = returnNumber; }
    
    public String getReturnType() { return returnType; }
    public void setReturnType(String returnType) { this.returnType = returnType; }
    
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    
    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public String getReturnStatus() { return returnStatus; }
    public void setReturnStatus(String returnStatus) { this.returnStatus = returnStatus; }
    
    public BigDecimal getRefundAmount() { return refundAmount; }
    public void setRefundAmount(BigDecimal refundAmount) { this.refundAmount = refundAmount; }
    
    public BigDecimal getRestockingFee() { return restockingFee; }
    public void setRestockingFee(BigDecimal restockingFee) { this.restockingFee = restockingFee; }
    
    public BigDecimal getNetRefund() { return netRefund; }
    public void setNetRefund(BigDecimal netRefund) { this.netRefund = netRefund; }
    
    public String getRefundMethod() { return refundMethod; }
    public void setRefundMethod(String refundMethod) { this.refundMethod = refundMethod; }
    
    public String getShippingMethod() { return shippingMethod; }
    public void setShippingMethod(String shippingMethod) { this.shippingMethod = shippingMethod; }
    
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    
    public String getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(String approvalStatus) { this.approvalStatus = approvalStatus; }
    
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
        if (returnNumber == null || returnNumber.isEmpty()) {
            returnNumber = "RET-" + System.currentTimeMillis();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
