package com.bharaterp.repository;

import com.bharaterp.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByCompanyId(Long companyId);
    List<Budget> findByCategory(String category);
    List<Budget> findByStatus(String status);
    List<Budget> findByFiscalYear(String fiscalYear);
}
