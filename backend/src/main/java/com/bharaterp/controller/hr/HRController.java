package com.bharaterp.controller.hr;

import com.bharaterp.model.hr.Employee;
import com.bharaterp.model.hr.LeaveRequest;
import com.bharaterp.model.hr.Attendance;
import com.bharaterp.model.hr.Payroll;
import com.bharaterp.service.hr.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hr")
@CrossOrigin(origins = "*")
public class HRController {
    
    @Autowired
    private HRService hrService;
    
    // ===== Employee APIs =====
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(hrService.getAllEmployees());
    }
    
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return hrService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/employees/code/{code}")
    public ResponseEntity<Employee> getEmployeeByCode(@PathVariable String code) {
        return hrService.getEmployeeByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/employees/email/{email}")
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email) {
        return hrService.getEmployeeByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hrService.createEmployee(employee));
    }
    
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return ResponseEntity.ok(hrService.updateEmployee(id, employee));
    }
    
    @PutMapping("/employees/{id}/terminate")
    public ResponseEntity<Employee> terminateEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(hrService.terminateEmployee(id));
    }
    
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        hrService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    
    // ===== Leave APIs =====
    @GetMapping("/leaves")
    public ResponseEntity<List<LeaveRequest>> getAllLeaves() {
        return ResponseEntity.ok(hrService.getAllLeaves());
    }
    
    @GetMapping("/leaves/{id}")
    public ResponseEntity<LeaveRequest> getLeaveById(@PathVariable Long id) {
        return hrService.getLeaveById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/leaves")
    public ResponseEntity<LeaveRequest> createLeave(@RequestBody LeaveRequest leave) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hrService.createLeaveRequest(leave));
    }
    
    @PutMapping("/leaves/{id}/approve")
    public ResponseEntity<LeaveRequest> approveLeave(@PathVariable Long id, @RequestParam String approvedBy) {
        return ResponseEntity.ok(hrService.approveLeave(id, approvedBy));
    }
    
    @PutMapping("/leaves/{id}/reject")
    public ResponseEntity<LeaveRequest> rejectLeave(@PathVariable Long id, @RequestParam String reason) {
        return ResponseEntity.ok(hrService.rejectLeave(id, reason));
    }
    
    @DeleteMapping("/leaves/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
        hrService.deleteLeave(id);
        return ResponseEntity.noContent().build();
    }
}
