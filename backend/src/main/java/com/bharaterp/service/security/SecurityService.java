package com.bharaterp.service.security;

import com.bharaterp.model.security.SecurityAudit;
import com.bharaterp.repository.security.SecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SecurityService {
    
    @Autowired
    private SecurityRepository securityRepository;
    
    public SecurityAudit logEvent(SecurityAudit audit) {
        audit.setCreatedAt(LocalDateTime.now());
        return securityRepository.save(audit);
    }
    
    public List<SecurityAudit> getAuditLogs() {
        return securityRepository.findAll();
    }
    
    public List<SecurityAudit> getAuditLogsByUser(Long userId) {
        return securityRepository.findByUserId(userId);
    }
    
    public List<SecurityAudit> getAlerts() {
        return securityRepository.findByIsAlertTrue();
    }
    
    public SecurityAudit logLoginAttempt(Long userId, String username, String ipAddress, Boolean success) {
        SecurityAudit audit = new SecurityAudit();
        audit.setEventType("LOGIN");
        audit.setUserId(userId);
        audit.setUsername(username);
        audit.setIpAddress(ipAddress);
        audit.setStatus(success ? "SUCCESS" : "FAILURE");
        audit.setIsAlert(!success);
        if (!success) audit.setAlertLevel("WARNING");
        return securityRepository.save(audit);
    }
}
