package com.bharaterp.repository.finance_adv;

import com.bharaterp.model.finance_adv.RevenueRecognition;
import com.bharaterp.model.finance_adv.TreasuryManagement;
import com.bharaterp.model.finance_adv.CostAccounting;
import com.bharaterp.model.finance_adv.IntercompanyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FinanceAdvRepository extends JpaRepository<RevenueRecognition, Long> {
    
    // Revenue Recognition Queries
    List<RevenueRecognition> findByCustomerId(Long customerId);
    List<RevenueRecognition> findByStatus(String status);
    List<RevenueRecognition> findByRevenueType(String revenueType);
    
    // Treasury Management Queries
    List<TreasuryManagement> findByTreasuryType(String treasuryType);
    List<TreasuryManagement> findByStatus(String status);
    List<TreasuryManagement> findByMaturityDateBefore(LocalDateTime date);
    
    // Cost Accounting Queries
    List<CostAccounting> findByCostCenterType(String costCenterType);
    List<CostAccounting> findByCostElement(String costElement);
    List<CostAccounting> findByPeriodAndYear(String period, String year);
    
    // Intercompany Transaction Queries
    List<IntercompanyTransaction> findBySourceCompanyId(Long companyId);
    List<IntercompanyTransaction> findByTargetCompanyId(Long companyId);
    List<IntercompanyTransaction> findByStatus(String status);
}
