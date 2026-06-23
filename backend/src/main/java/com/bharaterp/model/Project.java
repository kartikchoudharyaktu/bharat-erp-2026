package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String projectCode;
    
    @Column(nullable = false)
    private String projectName;
    
    private String description;
    private String clientName;
    
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualEndDate;
    
    private String status = "PLANNED"; // PLANNED, IN_PROGRESS, ON_HOLD, COMPLETED, CANCELLED
    
    private String priority = "MEDIUM"; // LOW, MEDIUM, HIGH, URGENT
    private String category;
    
    private Double budget = 0.0;
    private Double spent = 0.0;
    private Double remaining = 0.0;
    
    @Column(name = "project_manager")
    private String projectManager;
    
    @Column(name = "project_manager_id")
    private Long projectManagerId;
    
    private String notes;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}