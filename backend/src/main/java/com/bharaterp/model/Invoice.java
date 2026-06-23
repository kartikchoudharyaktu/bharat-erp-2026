package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String invoiceNumber;
    
    @Column(nullable = false)
    private String invoiceDate;
    
    @Column(name = "customer_id")
    private Long customerId;
    
    @Column(name = "customer_name")
    private String customerName;
    
    @Column(name = "customer_gstin")
    private String customerGstin;
    
    @Column(name = "customer_address")
    private String customerAddress;
    
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
    private Integer posted = 0;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}