package com.bharaterp.service;

import com.bharaterp.model.Leave;
import com.bharaterp.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {
    
    @Autowired
    private LeaveRepository leaveRepository;
    
    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }
    
    public Optional<Leave> getLeaveById(Long id) {
        return leaveRepository.findById(id);
    }
    
    public Leave createLeave(Leave leave) {
        leave.setStatus("PENDING");
        return leaveRepository.save(leave);
    }
    
    public Leave updateLeave(Long id, Leave leaveDetails) {
        Leave leave = leaveRepository.findById(id).orElseThrow();
        leave.setStartDate(leaveDetails.getStartDate());
        leave.setEndDate(leaveDetails.getEndDate());
        leave.setDays(leaveDetails.getDays());
        leave.setReason(leaveDetails.getReason());
        leave.setStatus(leaveDetails.getStatus());
        return leaveRepository.save(leave);
    }
    
    public Leave approveLeave(Long id, String approvedBy) {
        Leave leave = leaveRepository.findById(id).orElseThrow();
        leave.setStatus("APPROVED");
        leave.setApprovedBy(approvedBy);
        return leaveRepository.save(leave);
    }
    
    public Leave rejectLeave(Long id) {
        Leave leave = leaveRepository.findById(id).orElseThrow();
        leave.setStatus("REJECTED");
        return leaveRepository.save(leave);
    }
    
    public void deleteLeave(Long id) {
        leaveRepository.deleteById(id);
    }
    
    public List<Leave> getLeavesByEmployee(Long employeeId) {
        return leaveRepository.findAll();
    }
}
