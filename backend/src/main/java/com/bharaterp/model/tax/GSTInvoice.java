package com.bharaterp.model.tax;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "gst_invoices")
public class GSTInvoice {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String invoiceNumber;
    
    @Column(nullable = false)
    private String invoiceType; // TAX_INVOICE, BILL_OF_SUPPLY, EXPORT_INVOICE
    
    @Column(nullable = false)
    private Long customerId;
    
    private String customerName;
    private String customerGstin;
    private String customerAddress;
    private String customerState;
    private String customerStateCode;
    
    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalTaxableAmount = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalCGST = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalSGST = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalIGST = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalGST = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal grandTotal = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal amountInWords; // Actually String, but keeping for compatibility
    
    private String status; // DRAFT, FINAL, CANCELLED
    
    private String irn; // Invoice Reference Number (E-Invoicing)
    private String qrCode;
    private String eWayBillNumber;
    
    private String createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    
    public String getInvoiceType() { return invoiceType; }
    public void setInvoiceType(String invoiceType) { this.invoiceType = invoiceType; }
    
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    
    public String getCustomerGstin() { return customerGstin; }
    public void setCustomerGstin(String customerGstin) { this.customerGstin = customerGstin; }
    
    public String getCustomerAddress() { return customerAddress; }
    public void setCustomerAddress(String customerAddress) { this.customerAddress = customerAddress; }
    
    public String getCustomerState() { return customerState; }
    public void setCustomerState(String customerState) { this.customerState = customerState; }
    
    public String getCustomerStateCode() { return customerStateCode; }
    public void setCustomerStateCode(String customerStateCode) { this.customerStateCode = customerStateCode; }
    
    public LocalDateTime getInvoiceDate() { return invoiceDate; }
    public void setInvoiceDate(LocalDateTime invoiceDate) { this.invoiceDate = invoiceDate; }
    
    public LocalDateTime getDueDate() { return dueDate; }
    public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
    
    public BigDecimal getTotalTaxableAmount() { return totalTaxableAmount; }
    public void setTotalTaxableAmount(BigDecimal totalTaxableAmount) { this.totalTaxableAmount = totalTaxableAmount; }
    
    public BigDecimal getTotalCGST() { return totalCGST; }
    public void setTotalCGST(BigDecimal totalCGST) { this.totalCGST = totalCGST; }
    
    public BigDecimal getTotalSGST() { return totalSGST; }
    public void setTotalSGST(BigDecimal totalSGST) { this.totalSGST = totalSGST; }
    
    public BigDecimal getTotalIGST() { return totalIGST; }
    public void setTotalIGST(BigDecimal totalIGST) { this.totalIGST = totalIGST; }
    
    public BigDecimal getTotalGST() { return totalGST; }
    public void setTotalGST(BigDecimal totalGST) { this.totalGST = totalGST; }
    
    public BigDecimal getGrandTotal() { return grandTotal; }
    public void setGrandTotal(BigDecimal grandTotal) { this.grandTotal = grandTotal; }
    
    public BigDecimal getAmountInWords() { return amountInWords; }
    public void setAmountInWords(BigDecimal amountInWords) { this.amountInWords = amountInWords; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getIrn() { return irn; }
    public void setIrn(String irn) { this.irn = irn; }
    
    public String getQrCode() { return qrCode; }
    public void setQrCode(String qrCode) { this.qrCode = qrCode; }
    
    public String getEWayBillNumber() { return eWayBillNumber; }
    public void setEWayBillNumber(String eWayBillNumber) { this.eWayBillNumber = eWayBillNumber; }
    
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) status = "DRAFT";
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
