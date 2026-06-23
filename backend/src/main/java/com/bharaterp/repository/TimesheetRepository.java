package com.bharaterp.repository;

import com.bharaterp.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findByEmployeeId(Long employeeId);
    List<Timesheet> findByTaskId(Long taskId);
    List<Timesheet> findByProjectId(Long projectId);
    List<Timesheet> findByEntryDateBetween(LocalDate startDate, LocalDate endDate);
}
