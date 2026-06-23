package com.bharaterp.service.hr;

import com.bharaterp.model.hr.Employee;
import com.bharaterp.model.hr.LeaveRequest;
import com.bharaterp.model.hr.Attendance;
import com.bharaterp.model.hr.Payroll;
import com.bharaterp.repository.hr.HRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Service
public class HRService {
    
    @Autowired
    private HRRepository hrRepository;
    
    // ===== Employee Operations =====
    public List<Employee> getAllEmployees() {
        return hrRepository.findAll();
    }
    
    public Optional<Employee> getEmployeeById(Long id) {
        return hrRepository.findById(id);
    }
    
    public Optional<Employee> getEmployeeByCode(String code) {
        return hrRepository.findByEmployeeCode(code);
    }
    
    public Optional<Employee> getEmployeeByEmail(String email) {
        return hrRepository.findByEmail(email);
    }
    
    public Employee createEmployee(Employee employee) {
        if (employee.getEmployeeCode() == null || employee.getEmployeeCode().isEmpty()) {
            employee.setEmployeeCode("EMP-" + System.currentTimeMillis());
        }
        return hrRepository.save(employee);
    }
    
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = hrRepository.findById(id).orElseThrow();
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPhone(employeeDetails.getPhone());
        employee.setMobile(employeeDetails.getMobile());
        employee.setDepartment(employeeDetails.getDepartment());
        employee.setDesignation(employeeDetails.getDesignation());
        employee.setBasicSalary(employeeDetails.getBasicSalary());
        employee.setAllowances(employeeDetails.getAllowances());
        employee.setNetSalary(employeeDetails.getNetSalary());
        employee.setBankName(employeeDetails.getBankName());
        employee.setAccountNumber(employeeDetails.getAccountNumber());
        employee.setIfscCode(employeeDetails.getIfscCode());
        employee.setStatus(employeeDetails.getStatus());
        return hrRepository.save(employee);
    }
    
    public Employee terminateEmployee(Long id) {
        Employee employee = hrRepository.findById(id).orElseThrow();
        employee.setStatus("TERMINATED");
        employee.setExitDate(LocalDate.now());
        return hrRepository.save(employee);
    }
    
    public void deleteEmployee(Long id) {
        hrRepository.deleteById(id);
    }
    
    // ===== Leave Operations =====
    public List<LeaveRequest> getAllLeaves() {
        return hrRepository.findAll();
    }
    
    public Optional<LeaveRequest> getLeaveById(Long id) {
        return hrRepository.findById(id);
    }
    
    public LeaveRequest createLeaveRequest(LeaveRequest leave) {
        return hrRepository.save(leave);
    }
    
    public LeaveRequest approveLeave(Long id, String approvedBy) {
        LeaveRequest leave = hrRepository.findById(id).orElseThrow();
        leave.setStatus("APPROVED");
        leave.setApprovedBy(approvedBy);
        leave.setApprovedDate(LocalDate.now());
        return hrRepository.save(leave);
    }
    
    public LeaveRequest rejectLeave(Long id, String reason) {
        LeaveRequest leave = hrRepository.findById(id).orElseThrow();
        leave.setStatus("REJECTED");
        leave.setRejectionReason(reason);
        return hrRepository.save(leave);
    }
    
    public void deleteLeave(Long id) {
        hrRepository.deleteById(id);
    }
    
    // ===== Attendance Operations =====
    public List<Attendance> getAllAttendance() {
        return hrRepository.findAll();
    }
    
    public Attendance markAttendance(Attendance attendance) {
        return hrRepository.save(attendance);
    }
    
    public List<Attendance> getAttendanceByEmployee(Long employeeId) {
        return hrRepository.findByEmployeeId(employeeId);
    }
    
    // ===== Payroll Operations =====
    public List<Payroll> getAllPayrolls() {
        return hrRepository.findAll();
    }
    
    public Payroll generatePayroll(Payroll payroll) {
        // Calculate net salary
        BigDecimal grossSalary = payroll.getBasicSalary().add(payroll.getAllowances());
        payroll.setGrossSalary(grossSalary);
        
        BigDecimal totalDeductions = payroll.getPfContribution()
                .add(payroll.getEsiContribution())
                .add(payroll.getTaxDeduction())
                .add(payroll.getOtherDeductions());
        payroll.setTotalDeductions(totalDeductions);
        
        BigDecimal netSalary = grossSalary.subtract(totalDeductions);
        payroll.setNetSalary(netSalary);
        payroll.setStatus("GENERATED");
        
        return hrRepository.save(payroll);
    }
}
