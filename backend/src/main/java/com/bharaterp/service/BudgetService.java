package com.bharaterp.service;

import com.bharaterp.model.Budget;
import com.bharaterp.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    
    @Autowired
    private BudgetRepository budgetRepository;
    
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }
    
    public Optional<Budget> getBudgetById(Long id) {
        return budgetRepository.findById(id);
    }
    
    public Budget createBudget(Budget budget) {
        budget.setBudgetCode("BUD-" + System.currentTimeMillis());
        budget.setRemaining(budget.getAmount());
        return budgetRepository.save(budget);
    }
    
    public Budget updateBudget(Long id, Budget budgetDetails) {
        Budget budget = budgetRepository.findById(id).orElseThrow();
        budget.setBudgetName(budgetDetails.getBudgetName());
        budget.setCategory(budgetDetails.getCategory());
        budget.setAmount(budgetDetails.getAmount());
        budget.setSpent(budgetDetails.getSpent());
        budget.setRemaining(budgetDetails.getAmount() - budgetDetails.getSpent());
        budget.setStatus(budgetDetails.getStatus());
        return budgetRepository.save(budget);
    }
    
    public void deleteBudget(Long id) {
        budgetRepository.deleteById(id);
    }
    
    public List<Budget> getBudgetsByCompany(Long companyId) {
        return budgetRepository.findByCompanyId(companyId);
    }
    
    public List<Budget> getBudgetsByCategory(String category) {
        return budgetRepository.findByCategory(category);
    }
}
