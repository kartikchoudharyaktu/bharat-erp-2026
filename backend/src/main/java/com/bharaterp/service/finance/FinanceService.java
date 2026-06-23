package com.bharaterp.service.finance;

import com.bharaterp.model.finance.GeneralLedger;
import com.bharaterp.repository.finance.GeneralLedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class FinanceService {
    
    @Autowired
    private GeneralLedgerRepository generalLedgerRepository;
    
    // General Ledger Operations
    public List<GeneralLedger> getAllGeneralLedgers() {
        return generalLedgerRepository.findAll();
    }
    
    public Optional<GeneralLedger> getGeneralLedgerById(Long id) {
        return generalLedgerRepository.findById(id);
    }
    
    public Optional<GeneralLedger> getGeneralLedgerByCode(String code) {
        return generalLedgerRepository.findByAccountCode(code);
    }
    
    public GeneralLedger createGeneralLedger(GeneralLedger ledger) {
        return generalLedgerRepository.save(ledger);
    }
    
    public GeneralLedger updateGeneralLedger(Long id, GeneralLedger ledgerDetails) {
        GeneralLedger ledger = generalLedgerRepository.findById(id).orElseThrow();
        ledger.setAccountName(ledgerDetails.getAccountName());
        ledger.setAccountType(ledgerDetails.getAccountType());
        ledger.setSubType(ledgerDetails.getSubType());
        ledger.setCurrentBalance(ledgerDetails.getCurrentBalance());
        ledger.setStatus(ledgerDetails.getStatus());
        return generalLedgerRepository.save(ledger);
    }
    
    public void deleteGeneralLedger(Long id) {
        generalLedgerRepository.deleteById(id);
    }
    
    // Financial Calculations
    public BigDecimal calculateTotalDebit(Long companyId) {
        List<GeneralLedger> ledgers = generalLedgerRepository.findByCompanyId(companyId);
        return ledgers.stream()
                .map(GeneralLedger::getTotalDebit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public BigDecimal calculateTotalCredit(Long companyId) {
        List<GeneralLedger> ledgers = generalLedgerRepository.findByCompanyId(companyId);
        return ledgers.stream()
                .map(GeneralLedger::getTotalCredit)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public BigDecimal getBalanceByAccountCode(String accountCode) {
        Optional<GeneralLedger> ledger = generalLedgerRepository.findByAccountCode(accountCode);
        return ledger.map(GeneralLedger::getCurrentBalance).orElse(BigDecimal.ZERO);
    }
}
