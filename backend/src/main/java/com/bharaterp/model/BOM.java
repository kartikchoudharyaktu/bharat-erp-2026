package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "bom")
@Data
public class BOM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String bomCode;
    
    @Column(nullable = false)
    private String productName;
    
    private String productCode;
    private String description;
    private String version = "1.0";
    
    private String status = "ACTIVE"; // ACTIVE, INACTIVE, DRAFT
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}