package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "po_items")
@Data
public class POItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "po_id")
    private Long poId;
    
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