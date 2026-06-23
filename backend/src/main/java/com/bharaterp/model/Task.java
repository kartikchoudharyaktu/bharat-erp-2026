package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String taskName;
    
    @Column(name = "project_id")
    private Long projectId;
    
    private String description;
    private String assignedTo;
    private Long assignedToId;
    
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate actualEndDate;
    
    private String status = "PENDING"; // PENDING, IN_PROGRESS, COMPLETED, BLOCKED
    
    private String priority = "MEDIUM"; // LOW, MEDIUM, HIGH, URGENT
    private Integer progress = 0; // 0-100
    
    private String comments;
    private Double estimatedHours = 0.0;
    private Double actualHours = 0.0;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}