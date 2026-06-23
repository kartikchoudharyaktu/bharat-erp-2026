package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 20)
    private String code;
    
    @Column(nullable = false, length = 100)
    private String name;
    
    @Column(length = 20)
    private String hsn;
    
    @Column(length = 10)
    private String unit;
    
    @Column(name = "gst_rate")
    private Double gstRate = 18.0;
    
    @Column(name = "purchase_price")
    private Double purchasePrice = 0.0;
    
    @Column(name = "selling_price")
    private Double sellingPrice = 0.0;
    
    private Double stock = 0.0;
    
    @Column(name = "min_stock")
    private Double minStock = 0.0;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}