package com.bharaterp.controller.finance_adv;

import com.bharaterp.model.finance_adv.RevenueRecognition;
import com.bharaterp.model.finance_adv.TreasuryManagement;
import com.bharaterp.model.finance_adv.CostAccounting;
import com.bharaterp.model.finance_adv.IntercompanyTransaction;
import com.bharaterp.service.finance_adv.FinanceAdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/finance-adv")
@CrossOrigin(origins = "*")
public class FinanceAdvController {
    
    @Autowired
    private FinanceAdvService financeAdvService;
    
    // ===== Revenue Recognition APIs =====
    @GetMapping("/revenue")
    public ResponseEntity<List<RevenueRecognition>> getAllRevenueContracts() {
        return ResponseEntity.ok(financeAdvService.getAllRevenueContracts());
    }
    
    @PostMapping("/revenue")
    public ResponseEntity<RevenueRecognition> createRevenueContract(@RequestBody RevenueRecognition contract) {
        return ResponseEntity.status(HttpStatus.CREATED).body(financeAdvService.createRevenueContract(contract));
    }
    
    @PutMapping("/revenue/{id}/recognize")
    public ResponseEntity<RevenueRecognition> recognizeRevenue(@PathVariable Long id, @RequestParam Integer periods) {
        return ResponseEntity.ok(financeAdvService.recognizeRevenue(id, periods));
    }
    
    // ===== Treasury APIs =====
    @GetMapping("/treasury")
    public ResponseEntity<List<TreasuryManagement>> getAllTreasuryInstruments() {
        return ResponseEntity.ok(financeAdvService.getAllTreasuryInstruments());
    }
    
    @PostMapping("/treasury")
    public ResponseEntity<TreasuryManagement> createTreasuryInstrument(@RequestBody TreasuryManagement treasury) {
        return ResponseEntity.status(HttpStatus.CREATED).body(financeAdvService.createTreasuryInstrument(treasury));
    }
    
    @PutMapping("/treasury/{id}/value")
    public ResponseEntity<TreasuryManagement> updateTreasuryValue(@PathVariable Long id, @RequestParam BigDecimal marketValue) {
        return ResponseEntity.ok(financeAdvService.updateTreasuryValue(id, marketValue));
    }
    
    @PutMapping("/treasury/{id}/mature")
    public ResponseEntity<TreasuryManagement> matureTreasury(@PathVariable Long id) {
        return ResponseEntity.ok(financeAdvService.matureTreasury(id));
    }
    
    // ===== Cost Accounting APIs =====
    @GetMapping("/cost")
    public ResponseEntity<List<CostAccounting>> getAllCosts() {
        return ResponseEntity.ok(financeAdvService.getAllCosts());
    }
    
    @PostMapping("/cost")
    public ResponseEntity<CostAccounting> createCost(@RequestBody CostAccounting cost) {
        return ResponseEntity.status(HttpStatus.CREATED).body(financeAdvService.createCost(cost));
    }
    
    @GetMapping("/cost/total/{costElement}")
    public ResponseEntity<BigDecimal> getTotalCostByType(@PathVariable String costElement) {
        return ResponseEntity.ok(financeAdvService.getTotalCostByType(costElement));
    }
    
    // ===== Intercompany APIs =====
    @GetMapping("/intercompany")
    public ResponseEntity<List<IntercompanyTransaction>> getAllIntercompanyTransactions() {
        return ResponseEntity.ok(financeAdvService.getAllIntercompanyTransactions());
    }
    
    @PostMapping("/intercompany")
    public ResponseEntity<IntercompanyTransaction> createIntercompanyTransaction(@RequestBody IntercompanyTransaction transaction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(financeAdvService.createIntercompanyTransaction(transaction));
    }
    
    @PutMapping("/intercompany/{id}/approve")
    public ResponseEntity<IntercompanyTransaction> approveIntercompanyTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(financeAdvService.approveIntercompanyTransaction(id));
    }
    
    @PutMapping("/intercompany/{id}/settle")
    public ResponseEntity<IntercompanyTransaction> settleIntercompanyTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(financeAdvService.settleIntercompanyTransaction(id));
    }
}
