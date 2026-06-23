package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leaves")
@Data
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "employee_id")
    private Long employeeId;
    
    @Column(nullable = false)
    private String employeeName;
    
    @Column(nullable = false)
    private String leaveType; // ANNUAL, SICK, CASUAL, MATERNITY, PATERNITY
    
    @Column(nullable = false)
    private LocalDate startDate;
    
    @Column(nullable = false)
    private LocalDate endDate;
    
    @Column(nullable = false)
    private Integer totalDays;
    
    private String reason;
    private String status = "PENDING"; // PENDING, APPROVED, REJECTED, CANCELLED
    
    @Column(name = "approved_by")
    private Long approvedBy;
    
    @Column(name = "approved_by_name")
    private String approvedByName;
    
    private LocalDate approvalDate;
    private String rejectionReason;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}