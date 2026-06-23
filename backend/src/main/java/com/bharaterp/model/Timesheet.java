package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "timesheets")
@Data
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "employee_id")
    private Long employeeId;
    
    @Column(nullable = false)
    private String employeeName;
    
    @Column(name = "project_id")
    private Long projectId;
    
    @Column(name = "task_id")
    private Long taskId;
    
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(nullable = false)
    private Double hours = 0.0;
    
    private String description;
    private String status = "PENDING"; // PENDING, APPROVED, REJECTED
    
    @Column(name = "approved_by")
    private Long approvedBy;
    
    @Column(name = "approved_by_name")
    private String approvedByName;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}