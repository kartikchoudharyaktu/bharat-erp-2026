package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "bom_items")
@Data
public class BOMItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "bom_id")
    private Long bomId;
    
    @Column(nullable = false)
    private String componentName;
    
    private String componentCode;
    private String componentType; // RAW, SEMI_FINISHED, FINISHED
    
    @Column(nullable = false)
    private Double quantity = 0.0;
    
    private String unit;
    private Double cost = 0.0;
    private String supplier;
}