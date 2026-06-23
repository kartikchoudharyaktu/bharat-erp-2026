package com.bharaterp.controller;

import com.bharaterp.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin(origins = "*")
public class CompanyController {
    
    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(null);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(null);
    }
    
    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        return ResponseEntity.ok(null);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
}
