package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String action; // LOGIN, LOGOUT, CREATE, UPDATE, DELETE, VIEW, EXPORT, IMPORT, APPROVE, REJECT
    
    @Column(nullable = false)
    private String module; // CUSTOMER, PRODUCT, INVOICE, PO, EMPLOYEE, LEAVE, PROJECT, TASK, COMPANY, BUDGET, REPORT, AI, SECURITY, SETTINGS
    
    @Column(nullable = false)
    private String entityType;
    
    private Long entityId;
    
    @Column(columnDefinition = "TEXT")
    private String oldValue;
    
    @Column(columnDefinition = "TEXT")
    private String newValue;
    
    private String ipAddress;
    private String userAgent;
    private String details;
    
    private String status = "SUCCESS"; // SUCCESS, FAILURE, WARNING
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}