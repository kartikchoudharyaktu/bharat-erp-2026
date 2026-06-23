package com.bharaterp.controller.security;

import com.bharaterp.model.security.SecurityAudit;
import com.bharaterp.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/security")
@CrossOrigin(origins = "*")
public class SecurityController {
    
    @Autowired
    private SecurityService securityService;
    
    @PostMapping("/audit/log")
    public ResponseEntity<SecurityAudit> logEvent(@RequestBody SecurityAudit audit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(securityService.logEvent(audit));
    }
    
    @GetMapping("/audit/logs")
    public ResponseEntity<List<SecurityAudit>> getAuditLogs() {
        return ResponseEntity.ok(securityService.getAuditLogs());
    }
    
    @GetMapping("/audit/user/{userId}")
    public ResponseEntity<List<SecurityAudit>> getAuditLogsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(securityService.getAuditLogsByUser(userId));
    }
    
    @GetMapping("/audit/alerts")
    public ResponseEntity<List<SecurityAudit>> getAlerts() {
        return ResponseEntity.ok(securityService.getAlerts());
    }
    
    @PostMapping("/audit/login")
    public ResponseEntity<SecurityAudit> logLogin(
            @RequestParam Long userId,
            @RequestParam String username,
            @RequestParam String ipAddress,
            @RequestParam Boolean success) {
        return ResponseEntity.ok(securityService.logLoginAttempt(userId, username, ipAddress, success));
    }
}
