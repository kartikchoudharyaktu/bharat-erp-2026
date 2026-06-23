package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "production_orders")
@Data
public class ProductionOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String orderNumber;
    
    @Column(nullable = false)
    private String productName;
    
    private String productCode;
    private Double quantity = 0.0;
    private Double completedQuantity = 0.0;
    
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualEndDate;
    
    private String status = "PLANNED"; // PLANNED, IN_PROGRESS, COMPLETED, CANCELLED
    
    private String priority = "MEDIUM"; // LOW, MEDIUM, HIGH, URGENT
    
    @Column(name = "bom_id")
    private Long bomId;
    
    private String notes;
    private Double estimatedCost = 0.0;
    private Double actualCost = 0.0;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}