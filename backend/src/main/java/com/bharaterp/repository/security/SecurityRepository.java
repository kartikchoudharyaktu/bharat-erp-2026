package com.bharaterp.repository.security;

import com.bharaterp.model.security.SecurityAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityAudit, Long> {
    
    List<SecurityAudit> findByUserId(Long userId);
    List<SecurityAudit> findByEventType(String eventType);
    List<SecurityAudit> findByStatus(String status);
    List<SecurityAudit> findByIsAlertTrue();
    List<SecurityAudit> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
