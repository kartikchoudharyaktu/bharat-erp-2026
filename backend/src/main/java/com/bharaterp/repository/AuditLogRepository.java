package com.bharaterp.repository;

import com.bharaterp.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUsernameOrderByCreatedAtDesc(String username);
    List<AuditLog> findByModuleOrderByCreatedAtDesc(String module);
}