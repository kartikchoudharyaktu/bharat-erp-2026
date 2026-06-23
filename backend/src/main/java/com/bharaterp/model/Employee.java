package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String employeeCode;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    private String email;
    private String phone;
    private String gender;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    
    private String department;
    private String designation;
    private String reportingManager;
    
    private Double basicSalary = 0.0;
    private Double hra = 0.0;
    private Double da = 0.0;
    private Double otherAllowances = 0.0;
    private Double deductions = 0.0;
    private Double netSalary = 0.0;
    
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String panNumber;
    private String uanNumber;
    
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String emergencyContactRelation;
    
    private String address;
    private String city;
    private String state;
    private String pincode;
    
    private String status = "ACTIVE";
    private Boolean isActive = true;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}