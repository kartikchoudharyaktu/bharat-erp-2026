package com.bharaterp.controller.enhancement;

import com.bharaterp.model.enhancement.EmailToOrder;
import com.bharaterp.model.enhancement.BankReconciliationAI;
import com.bharaterp.model.enhancement.DigitalTwin;
import com.bharaterp.service.enhancement.EnhancementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enhancement")
@CrossOrigin(origins = "*")
public class EnhancementController {
    
    @Autowired
    private EnhancementService enhancementService;
    
    // ===== Email to Order APIs =====
    @PostMapping("/email/process")
    public ResponseEntity<EmailToOrder> processEmail(@RequestBody EmailToOrder email) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enhancementService.processEmail(email));
    }
    
    @GetMapping("/emails")
    public ResponseEntity<List<EmailToOrder>> getAllEmails() {
        return ResponseEntity.ok(enhancementService.getAllEmails());
    }
    
    // ===== AI Reconciliation APIs =====
    @PostMapping("/reconciliation")
    public ResponseEntity<BankReconciliationAI> performReconciliation(@RequestBody BankReconciliationAI recon) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enhancementService.performReconciliation(recon));
    }
    
    @GetMapping("/reconciliation")
    public ResponseEntity<List<BankReconciliationAI>> getAllReconciliations() {
        return ResponseEntity.ok(enhancementService.getAllReconciliations());
    }
    
    @PutMapping("/reconciliation/{id}/resolve")
    public ResponseEntity<BankReconciliationAI> resolveReconciliation(@PathVariable Long id, @RequestParam String notes) {
        return ResponseEntity.ok(enhancementService.resolveReconciliation(id, notes));
    }
    
    // ===== Digital Twin APIs =====
    @PostMapping("/twin")
    public ResponseEntity<DigitalTwin> createTwin(@RequestBody DigitalTwin twin) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enhancementService.createTwin(twin));
    }
    
    @GetMapping("/twins")
    public ResponseEntity<List<DigitalTwin>> getAllTwins() {
        return ResponseEntity.ok(enhancementService.getAllTwins());
    }
    
    @PutMapping("/twin/{id}/simulate")
    public ResponseEntity<DigitalTwin> simulateTwin(@PathVariable Long id) {
        return ResponseEntity.ok(enhancementService.simulateTwin(id));
    }
}
