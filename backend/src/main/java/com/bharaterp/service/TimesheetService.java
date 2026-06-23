package com.bharaterp.service;

import com.bharaterp.model.Timesheet;
import com.bharaterp.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TimesheetService {
    
    @Autowired
    private TimesheetRepository timesheetRepository;
    
    public List<Timesheet> getAllTimesheets() {
        return timesheetRepository.findAll();
    }
    
    public Optional<Timesheet> getTimesheetById(Long id) {
        return timesheetRepository.findById(id);
    }
    
    public Timesheet createTimesheet(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }
    
    public Timesheet updateTimesheet(Long id, Timesheet timesheetDetails) {
        Timesheet timesheet = timesheetRepository.findById(id).orElseThrow();
        timesheet.setHoursWorked(timesheetDetails.getHoursWorked());
        timesheet.setDescription(timesheetDetails.getDescription());
        timesheet.setEntryDate(timesheetDetails.getEntryDate());
        timesheet.setStatus(timesheetDetails.getStatus());
        return timesheetRepository.save(timesheet);
    }
    
    public void deleteTimesheet(Long id) {
        timesheetRepository.deleteById(id);
    }
    
    public List<Timesheet> getTimesheetsByEmployee(Long employeeId) {
        return timesheetRepository.findByEmployeeId(employeeId);
    }
    
    public List<Timesheet> getTimesheetsByProject(Long projectId) {
        return timesheetRepository.findByProjectId(projectId);
    }
    
    public List<Timesheet> getTimesheetsByDateRange(LocalDate startDate, LocalDate endDate) {
        return timesheetRepository.findByEntryDateBetween(startDate, endDate);
    }
}
