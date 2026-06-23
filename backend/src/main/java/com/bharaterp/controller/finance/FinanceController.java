package com.bharaterp.controller.finance;

import com.bharaterp.model.finance.GeneralLedger;
import com.bharaterp.service.finance.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/finance")
@CrossOrigin(origins = "*")
public class FinanceController {
    
    @Autowired
    private FinanceService financeService;
    
    // General Ledger APIs
    @GetMapping("/general-ledger")
    public ResponseEntity<List<GeneralLedger>> getAllGeneralLedgers() {
        return ResponseEntity.ok(financeService.getAllGeneralLedgers());
    }
    
    @GetMapping("/general-ledger/{id}")
    public ResponseEntity<GeneralLedger> getGeneralLedgerById(@PathVariable Long id) {
        return financeService.getGeneralLedgerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/general-ledger/code/{code}")
    public ResponseEntity<GeneralLedger> getGeneralLedgerByCode(@PathVariable String code) {
        return financeService.getGeneralLedgerByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/general-ledger")
    public ResponseEntity<GeneralLedger> createGeneralLedger(@RequestBody GeneralLedger ledger) {
        return ResponseEntity.status(HttpStatus.CREATED).body(financeService.createGeneralLedger(ledger));
    }
    
    @PutMapping("/general-ledger/{id}")
    public ResponseEntity<GeneralLedger> updateGeneralLedger(@PathVariable Long id, @RequestBody GeneralLedger ledger) {
        return ResponseEntity.ok(financeService.updateGeneralLedger(id, ledger));
    }
    
    @DeleteMapping("/general-ledger/{id}")
    public ResponseEntity<Void> deleteGeneralLedger(@PathVariable Long id) {
        financeService.deleteGeneralLedger(id);
        return ResponseEntity.noContent().build();
    }
    
    // Financial Analytics APIs
    @GetMapping("/analytics/total-debit/{companyId}")
    public ResponseEntity<BigDecimal> getTotalDebit(@PathVariable Long companyId) {
        return ResponseEntity.ok(financeService.calculateTotalDebit(companyId));
    }
    
    @GetMapping("/analytics/total-credit/{companyId}")
    public ResponseEntity<BigDecimal> getTotalCredit(@PathVariable Long companyId) {
        return ResponseEntity.ok(financeService.calculateTotalCredit(companyId));
    }
    
    @GetMapping("/analytics/balance/{accountCode}")
    public ResponseEntity<BigDecimal> getBalanceByAccount(@PathVariable String accountCode) {
        return ResponseEntity.ok(financeService.getBalanceByAccountCode(accountCode));
    }
}
