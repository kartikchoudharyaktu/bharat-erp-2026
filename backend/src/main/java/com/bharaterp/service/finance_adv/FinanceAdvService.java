package com.bharaterp.service.finance_adv;

import com.bharaterp.model.finance_adv.RevenueRecognition;
import com.bharaterp.model.finance_adv.TreasuryManagement;
import com.bharaterp.model.finance_adv.CostAccounting;
import com.bharaterp.model.finance_adv.IntercompanyTransaction;
import com.bharaterp.repository.finance_adv.FinanceAdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FinanceAdvService {
    
    @Autowired
    private FinanceAdvRepository financeAdvRepository;
    
    // ===== Revenue Recognition =====
    public List<RevenueRecognition> getAllRevenueContracts() {
        return financeAdvRepository.findAll();
    }
    
    public Optional<RevenueRecognition> getRevenueContractById(Long id) {
        return financeAdvRepository.findById(id);
    }
    
    public RevenueRecognition createRevenueContract(RevenueRecognition contract) {
        // Calculate deferred revenue
        BigDecimal recognized = contract.getTotalContractValue().multiply(
            BigDecimal.valueOf(contract.getCompletedPeriods())
        ).divide(BigDecimal.valueOf(contract.getTotalPeriods()), 2, BigDecimal.ROUND_HALF_UP);
        contract.setRecognizedToDate(recognized);
        contract.setDeferredRevenue(contract.getTotalContractValue().subtract(recognized));
        return financeAdvRepository.save(contract);
    }
    
    public RevenueRecognition recognizeRevenue(Long id, Integer periods) {
        RevenueRecognition contract = financeAdvRepository.findById(id).orElseThrow();
        contract.setCompletedPeriods(contract.getCompletedPeriods() + periods);
        BigDecimal recognized = contract.getTotalContractValue().multiply(
            BigDecimal.valueOf(contract.getCompletedPeriods())
        ).divide(BigDecimal.valueOf(contract.getTotalPeriods()), 2, BigDecimal.ROUND_HALF_UP);
        contract.setRecognizedToDate(recognized);
        contract.setDeferredRevenue(contract.getTotalContractValue().subtract(recognized));
        if (contract.getCompletedPeriods() >= contract.getTotalPeriods()) {
            contract.setStatus("COMPLETED");
        }
        return financeAdvRepository.save(contract);
    }
    
    // ===== Treasury Management =====
    public List<TreasuryManagement> getAllTreasuryInstruments() {
        return financeAdvRepository.findAll();
    }
    
    public TreasuryManagement createTreasuryInstrument(TreasuryManagement treasury) {
        return financeAdvRepository.save(treasury);
    }
    
    public TreasuryManagement updateTreasuryValue(Long id, BigDecimal marketValue) {
        TreasuryManagement treasury = financeAdvRepository.findById(id).orElseThrow();
        treasury.setMarketValue(marketValue);
        BigDecimal gain = marketValue.subtract(treasury.getAmount());
        if (gain.compareTo(BigDecimal.ZERO) > 0) {
            treasury.setUnrealizedGain(gain);
        }
        return financeAdvRepository.save(treasury);
    }
    
    public TreasuryManagement matureTreasury(Long id) {
        TreasuryManagement treasury = financeAdvRepository.findById(id).orElseThrow();
        treasury.setStatus("MATURED");
        treasury.setRealizedGain(treasury.getUnrealizedGain());
        return financeAdvRepository.save(treasury);
    }
    
    // ===== Cost Accounting =====
    public List<CostAccounting> getAllCosts() {
        return financeAdvRepository.findAll();
    }
    
    public CostAccounting createCost(CostAccounting cost) {
        cost.setVariance(cost.getActualCost().subtract(cost.getBudgetedCost()));
        cost.setVariancePercentage(cost.getVariance().doubleValue() / cost.getBudgetedCost().doubleValue() * 100);
        return financeAdvRepository.save(cost);
    }
    
    public BigDecimal getTotalCostByType(String costElement) {
        return financeAdvRepository.findByCostElement(costElement)
                .stream()
                .map(CostAccounting::getActualCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    // ===== Intercompany Transactions =====
    public List<IntercompanyTransaction> getAllIntercompanyTransactions() {
        return financeAdvRepository.findAll();
    }
    
    public IntercompanyTransaction createIntercompanyTransaction(IntercompanyTransaction transaction) {
        // Calculate exchange rate if needed
        if (transaction.getExchangeRate() != null && transaction.getExchangeRate().compareTo(BigDecimal.ONE) != 0) {
            transaction.setAmountInSourceCurrency(transaction.getAmount());
            transaction.setAmountInTargetCurrency(
                transaction.getAmount().multiply(transaction.getExchangeRate())
            );
        }
        return financeAdvRepository.save(transaction);
    }
    
    public IntercompanyTransaction approveIntercompanyTransaction(Long id) {
        IntercompanyTransaction transaction = financeAdvRepository.findById(id).orElseThrow();
        transaction.setStatus("APPROVED");
        transaction.setApprovedDate(LocalDateTime.now());
        return financeAdvRepository.save(transaction);
    }
    
    public IntercompanyTransaction settleIntercompanyTransaction(Long id) {
        IntercompanyTransaction transaction = financeAdvRepository.findById(id).orElseThrow();
        transaction.setStatus("SETTLED");
        transaction.setSettlementDate(LocalDateTime.now());
        return financeAdvRepository.save(transaction);
    }
}
