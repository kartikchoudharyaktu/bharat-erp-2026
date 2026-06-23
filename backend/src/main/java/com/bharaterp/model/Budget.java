package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "budgets")
@Data
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "company_id")
    private Long companyId;
    
    @Column(nullable = false)
    private String budgetName;
    
    private String category;
    private String subCategory;
    private String department;
    
    private Double allocatedAmount = 0.0;
    private Double spentAmount = 0.0;
    private Double remainingAmount = 0.0;
    
    private String period = "MONTHLY"; // MONTHLY, QUARTERLY, YEARLY
    private String year;
    private String month;
    
    private String status = "ACTIVE";
    private String notes;
    
    @Column(name = "created_by")
    private String createdBy;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}