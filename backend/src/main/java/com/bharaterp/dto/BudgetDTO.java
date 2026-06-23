package com.bharaterp.dto;

import java.time.LocalDateTime;

public class BudgetDTO {
    private Long id; private Long companyId; private String budgetName; private String budgetCode;
    private String category; private Double amount; private Double spent; private Double remaining;
    private String fiscalYear; private LocalDateTime startDate; private LocalDateTime endDate;
    private String status; private Long createdBy;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    public String getBudgetName() { return budgetName; }
    public void setBudgetName(String budgetName) { this.budgetName = budgetName; }
    public String getBudgetCode() { return budgetCode; }
    public void setBudgetCode(String budgetCode) { this.budgetCode = budgetCode; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public Double getSpent() { return spent; }
    public void setSpent(Double spent) { this.spent = spent; }
    public Double getRemaining() { return remaining; }
    public void setRemaining(Double remaining) { this.remaining = remaining; }
    public String getFiscalYear() { return fiscalYear; }
    public void setFiscalYear(String fiscalYear) { this.fiscalYear = fiscalYear; }
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getCreatedBy() { return createdBy; }
    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }
}
