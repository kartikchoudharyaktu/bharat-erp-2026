package com.bharaterp.repository.enhancement;

import com.bharaterp.model.enhancement.EmailToOrder;
import com.bharaterp.model.enhancement.BankReconciliationAI;
import com.bharaterp.model.enhancement.DigitalTwin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EnhancementRepository extends JpaRepository<EmailToOrder, Long> {
    
    // EmailToOrder Queries
    List<EmailToOrder> findByStatus(String status);
    List<EmailToOrder> findBySenderEmail(String email);
    
    // BankReconciliationAI Queries
    List<BankReconciliationAI> findByStatus(String status);
    List<BankReconciliationAI> findByMatchType(String matchType);
    List<BankReconciliationAI> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    
    // DigitalTwin Queries
    List<DigitalTwin> findByTwinType(String twinType);
    List<DigitalTwin> findByStatus(String status);
}
