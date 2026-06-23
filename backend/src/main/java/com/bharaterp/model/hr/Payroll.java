package com.bharaterp.model.hr;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.math.BigDecimal;

@Entity
@Table(name = "payroll")
public class Payroll {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long employeeId;
    
    private String employeeName;
    private String employeeCode;
    
    @Column(nullable = false)
    private String month; // JANUARY, FEBRUARY, etc.
    
    @Column(nullable = false)
    private String year;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal basicSalary = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal allowances = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal grossSalary = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal pfContribution = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal esiContribution = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal taxDeduction = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal otherDeductions = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalDeductions = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal netSalary = BigDecimal.ZERO;
    
    private Integer presentDays = 0;
    private Integer leaveDays = 0;
    private Integer absentDays = 0;
    private Integer holidayDays = 0;
    
    private String status = "PENDING"; // PENDING, GENERATED, PAID
    
    private LocalDateTime paymentDate;
    private String paymentMode; // BANK_TRANSFER, CHEQUE, CASH
    
    private String bankReferenceNumber;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }
    
    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }
    
    public String getEmployeeCode() { return employeeCode; }
    public void setEmployeeCode(String employeeCode) { this.employeeCode = employeeCode; }
    
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
    
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    
    public BigDecimal getBasicSalary() { return basicSalary; }
    public void setBasicSalary(BigDecimal basicSalary) { this.basicSalary = basicSalary; }
    
    public BigDecimal getAllowances() { return allowances; }
    public void setAllowances(BigDecimal allowances) { this.allowances = allowances; }
    
    public BigDecimal getGrossSalary() { return grossSalary; }
    public void setGrossSalary(BigDecimal grossSalary) { this.grossSalary = grossSalary; }
    
    public BigDecimal getPfContribution() { return pfContribution; }
    public void setPfContribution(BigDecimal pfContribution) { this.pfContribution = pfContribution; }
    
    public BigDecimal getEsiContribution() { return esiContribution; }
    public void setEsiContribution(BigDecimal esiContribution) { this.esiContribution = esiContribution; }
    
    public BigDecimal getTaxDeduction() { return taxDeduction; }
    public void setTaxDeduction(BigDecimal taxDeduction) { this.taxDeduction = taxDeduction; }
    
    public BigDecimal getOtherDeductions() { return otherDeductions; }
    public void setOtherDeductions(BigDecimal otherDeductions) { this.otherDeductions = otherDeductions; }
    
    public BigDecimal getTotalDeductions() { return totalDeductions; }
    public void setTotalDeductions(BigDecimal totalDeductions) { this.totalDeductions = totalDeductions; }
    
    public BigDecimal getNetSalary() { return netSalary; }
    public void setNetSalary(BigDecimal netSalary) { this.netSalary = netSalary; }
    
    public Integer getPresentDays() { return presentDays; }
    public void setPresentDays(Integer presentDays) { this.presentDays = presentDays; }
    
    public Integer getLeaveDays() { return leaveDays; }
    public void setLeaveDays(Integer leaveDays) { this.leaveDays = leaveDays; }
    
    public Integer getAbsentDays() { return absentDays; }
    public void setAbsentDays(Integer absentDays) { this.absentDays = absentDays; }
    
    public Integer getHolidayDays() { return holidayDays; }
    public void setHolidayDays(Integer holidayDays) { this.holidayDays = holidayDays; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }
    
    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    
    public String getBankReferenceNumber() { return bankReferenceNumber; }
    public void setBankReferenceNumber(String bankReferenceNumber) { this.bankReferenceNumber = bankReferenceNumber; }
    
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
