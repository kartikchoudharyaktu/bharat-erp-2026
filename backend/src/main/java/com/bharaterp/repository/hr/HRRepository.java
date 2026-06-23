package com.bharaterp.repository.hr;

import com.bharaterp.model.hr.Employee;
import com.bharaterp.model.hr.LeaveRequest;
import com.bharaterp.model.hr.Attendance;
import com.bharaterp.model.hr.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HRRepository extends JpaRepository<Employee, Long> {
    
    // Employee Queries
    Optional<Employee> findByEmployeeCode(String employeeCode);
    Optional<Employee> findByEmail(String email);
    List<Employee> findByDepartment(String department);
    List<Employee> findByStatus(String status);
    List<Employee> findByCompanyId(Long companyId);
    
    // Leave Request Queries
    List<LeaveRequest> findByEmployeeId(Long employeeId);
    List<LeaveRequest> findByStatus(String status);
    List<LeaveRequest> findByEmployeeIdAndStatus(Long employeeId, String status);
    List<LeaveRequest> findByStartDateBetween(LocalDate start, LocalDate end);
    
    // Attendance Queries
    List<Attendance> findByEmployeeId(Long employeeId);
    List<Attendance> findByAttendanceDateBetween(LocalDate start, LocalDate end);
    List<Attendance> findByEmployeeIdAndAttendanceDateBetween(Long employeeId, LocalDate start, LocalDate end);
    
    // Payroll Queries
    List<Payroll> findByEmployeeId(Long employeeId);
    List<Payroll> findByMonthAndYear(String month, String year);
    List<Payroll> findByCompanyId(Long companyId);
}
