package com.bharaterp.repository.audit;

import com.bharaterp.model.audit.AuditTrail;
import com.bharaterp.model.audit.CACompliance;
import com.bharaterp.model.audit.CSCompliance;
import com.bharaterp.model.audit.AuditReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AuditRepository extends JpaRepository<AuditTrail, Long> {
    
    // Audit Trail Queries
    List<AuditTrail> findByModule(String module);
    List<AuditTrail> findByUserId(Long userId);
    List<AuditTrail> findByAction(String action);
    List<AuditTrail> findByIsVerified(Boolean isVerified);
    List<AuditTrail> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    
    // CA Compliance Queries
    List<CACompliance> findByComplianceType(String complianceType);
    List<CACompliance> findByFilingStatus(String filingStatus);
    List<CACompliance> findByFinancialYear(String financialYear);
    List<CACompliance> findByAuditStatus(String auditStatus);
    
    // CS Compliance Queries
    List<CSCompliance> findByComplianceType(String complianceType);
    List<CSCompliance> findByStatus(String status);
    List<CSCompliance> findByCompanyCode(String companyCode);
    
    // Audit Report Queries
    List<AuditReport> findByReportType(String reportType);
    List<AuditReport> findByStatus(String status);
    List<AuditReport> findByAuditorName(String auditorName);
}
