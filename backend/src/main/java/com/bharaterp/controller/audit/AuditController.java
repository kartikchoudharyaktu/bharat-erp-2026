package com.bharaterp.controller.audit;

import com.bharaterp.model.audit.AuditTrail;
import com.bharaterp.model.audit.CACompliance;
import com.bharaterp.model.audit.CSCompliance;
import com.bharaterp.model.audit.AuditReport;
import com.bharaterp.service.audit.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/audit")
@CrossOrigin(origins = "*")
public class AuditController {
    
    @Autowired
    private AuditService auditService;
    
    // ===== Audit Trail APIs =====
    @GetMapping("/trail")
    public ResponseEntity<List<AuditTrail>> getAllAuditTrails() {
        return ResponseEntity.ok(auditService.getAllAuditTrails());
    }
    
    @PostMapping("/trail")
    public ResponseEntity<AuditTrail> createAuditTrail(@RequestBody AuditTrail audit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(auditService.createAuditTrail(audit));
    }
    
    @PutMapping("/trail/{id}/verify")
    public ResponseEntity<AuditTrail> verifyAuditTrail(@PathVariable Long id) {
        return ResponseEntity.ok(auditService.verifyAuditTrail(id));
    }
    
    @GetMapping("/trail/module/{module}")
    public ResponseEntity<List<AuditTrail>> getAuditsByModule(@PathVariable String module) {
        return ResponseEntity.ok(auditService.getAuditsByModule(module));
    }
    
    // ===== CA Compliance APIs =====
    @GetMapping("/ca")
    public ResponseEntity<List<CACompliance>> getAllCACompliances() {
        return ResponseEntity.ok(auditService.getAllCACompliances());
    }
    
    @PostMapping("/ca")
    public ResponseEntity<CACompliance> createCACompliance(@RequestBody CACompliance compliance) {
        return ResponseEntity.status(HttpStatus.CREATED).body(auditService.createCACompliance(compliance));
    }
    
    @PutMapping("/ca/{id}/file")
    public ResponseEntity<CACompliance> fileCACompliance(@PathVariable Long id) {
        return ResponseEntity.ok(auditService.fileCACompliance(id));
    }
    
    @PutMapping("/ca/{id}/complete")
    public ResponseEntity<CACompliance> completeCACompliance(@PathVariable Long id) {
        return ResponseEntity.ok(auditService.completeCACompliance(id));
    }
    
    // ===== CS Compliance APIs =====
    @GetMapping("/cs")
    public ResponseEntity<List<CSCompliance>> getAllCSCompliances() {
        return ResponseEntity.ok(auditService.getAllCSCompliances());
    }
    
    @PostMapping("/cs")
    public ResponseEntity<CSCompliance> createCSCompliance(@RequestBody CSCompliance compliance) {
        return ResponseEntity.status(HttpStatus.CREATED).body(auditService.createCSCompliance(compliance));
    }
    
    @PutMapping("/cs/{id}/complete")
    public ResponseEntity<CSCompliance> completeCSCompliance(@PathVariable Long id) {
        return ResponseEntity.ok(auditService.completeCSCompliance(id));
    }
    
    // ===== Audit Report APIs =====
    @GetMapping("/reports")
    public ResponseEntity<List<AuditReport>> getAllAuditReports() {
        return ResponseEntity.ok(auditService.getAllAuditReports());
    }
    
    @PostMapping("/reports")
    public ResponseEntity<AuditReport> createAuditReport(@RequestBody AuditReport report) {
        return ResponseEntity.status(HttpStatus.CREATED).body(auditService.createAuditReport(report));
    }
    
    @PutMapping("/reports/{id}/publish")
    public ResponseEntity<AuditReport> publishAuditReport(@PathVariable Long id) {
        return ResponseEntity.ok(auditService.publishAuditReport(id));
    }
}
