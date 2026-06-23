package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "invoice_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "invoice_id")
    private Long invoiceId;
    
    @Column(name = "product_name")
    private String productName;
    
    private String hsn;
    private Double quantity = 0.0;
    private Double rate = 0.0;
    private Double amount = 0.0;
    
    @Column(name = "gst_rate")
    private Double gstRate = 0.0;
    
    @Column(name = "gst_amount")
    private Double gstAmount = 0.0;
    
    private Double total = 0.0;
}