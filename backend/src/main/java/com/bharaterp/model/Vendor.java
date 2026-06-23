package com.bharaterp.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "vendors")
@Data
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String code;
    
    @Column(nullable = false)
    private String name;
    private String gstin;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String state;
    private Double balance = 0.0;
    private Boolean isActive = true;
    private LocalDateTime createdAt;
}