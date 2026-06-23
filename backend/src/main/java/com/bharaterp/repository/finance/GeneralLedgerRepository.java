package com.bharaterp.repository.finance;

import com.bharaterp.model.finance.GeneralLedger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GeneralLedgerRepository extends JpaRepository<GeneralLedger, Long> {
    
    Optional<GeneralLedger> findByAccountCode(String accountCode);
    
    List<GeneralLedger> findByCompanyId(Long companyId);
    
    List<GeneralLedger> findByAccountType(String accountType);
    
    List<GeneralLedger> findByStatus(String status);
    
    List<GeneralLedger> findByCompanyIdAndStatus(Long companyId, String status);
}
