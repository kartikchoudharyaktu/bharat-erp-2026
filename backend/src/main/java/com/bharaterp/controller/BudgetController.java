package com.bharaterp.controller;

import com.bharaterp.model.Budget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "*")
public class BudgetController {
    
    @GetMapping
    public ResponseEntity<List<Budget>> getAllBudgets() {
        return ResponseEntity.ok(null);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        return ResponseEntity.ok(null);
    }
    
    @PostMapping
    public ResponseEntity<Budget> createBudget(@RequestBody Budget budget) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget budget) {
        return ResponseEntity.ok(null);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Budget>> getBudgetsByCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(null);
    }
}
