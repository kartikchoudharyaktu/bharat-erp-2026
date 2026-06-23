package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "companies")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String code;
    
    @Column(nullable = false)
    private String name;
    
    private String gstin;
    private String pan;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String phone;
    private String email;
    private String website;
    private String currency = "INR";
    private String timezone = "Asia/Kolkata";
    private String fiscalYearStart = "2026-04-01";
    
    private String logo;
    private String status = "ACTIVE";
    private Boolean isActive = true;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}