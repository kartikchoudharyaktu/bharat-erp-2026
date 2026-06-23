package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_orders")
@Data
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String poNumber;
    
    @Column(nullable = false)
    private String poDate;
    
    @Column(name = "vendor_id")
    private Long vendorId;
    
    @Column(name = "vendor_name")
    private String vendorName;
    
    @Column(name = "vendor_gstin")
    private String vendorGstin;
    
    private Double totalAmount = 0.0;
    private Double taxableAmount = 0.0;
    private Double cgst = 0.0;
    private Double sgst = 0.0;
    private Double igst = 0.0;
    
    @Column(name = "total_gst")
    private Double totalGst = 0.0;
    
    @Column(name = "grand_total")
    private Double grandTotal = 0.0;
    
    @Column(name = "amount_in_words")
    private String amountInWords;
    
    private String status = "DRAFT";
    private String deliveryDate;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}