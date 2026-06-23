package com.bharaterp.controller;

import com.bharaterp.model.Leave;
import com.bharaterp.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "*")
public class LeaveController {
    
    @Autowired
    private LeaveService leaveService;
    
    @GetMapping
    public ResponseEntity<List<Leave>> getAllLeaves() {
        return ResponseEntity.ok(leaveService.getAllLeaves());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Leave> getLeaveById(@PathVariable Long id) {
        return leaveService.getLeaveById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Leave> createLeave(@RequestBody Leave leave) {
        return ResponseEntity.status(HttpStatus.CREATED).body(leaveService.createLeave(leave));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Leave> updateLeave(@PathVariable Long id, @RequestBody Leave leave) {
        return ResponseEntity.ok(leaveService.updateLeave(id, leave));
    }
    
    @PutMapping("/{id}/approve")
    public ResponseEntity<Leave> approveLeave(@PathVariable Long id, @RequestParam String approvedBy) {
        return ResponseEntity.ok(leaveService.approveLeave(id, approvedBy));
    }
    
    @PutMapping("/{id}/reject")
    public ResponseEntity<Leave> rejectLeave(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.rejectLeave(id));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Leave>> getLeavesByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(leaveService.getLeavesByEmployee(employeeId));
    }
}
