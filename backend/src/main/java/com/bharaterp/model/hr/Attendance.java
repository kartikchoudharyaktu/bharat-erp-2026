package com.bharaterp.model.hr;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Entity
@Table(name = "attendance")
public class Attendance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Long employeeId;
    
    private String employeeName;
    
    @Column(nullable = false)
    private LocalDate attendanceDate;
    
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    
    private Double totalHours = 0.0;
    private Double overtimeHours = 0.0;
    
    private String attendanceType = "PRESENT"; // PRESENT, ABSENT, HALF_DAY, LEAVE, HOLIDAY
    
    private String checkInMethod; // BIOMETRIC, MOBILE, MANUAL, FACE_RECOGNITION
    private String checkOutMethod;
    
    private String checkInLocation;
    private String checkOutLocation;
    
    private String remarks;
    
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
    
    public LocalDate getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(LocalDate attendanceDate) { this.attendanceDate = attendanceDate; }
    
    public LocalDateTime getCheckInTime() { return checkInTime; }
    public void setCheckInTime(LocalDateTime checkInTime) { this.checkInTime = checkInTime; }
    
    public LocalDateTime getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(LocalDateTime checkOutTime) { this.checkOutTime = checkOutTime; }
    
    public Double getTotalHours() { return totalHours; }
    public void setTotalHours(Double totalHours) { this.totalHours = totalHours; }
    
    public Double getOvertimeHours() { return overtimeHours; }
    public void setOvertimeHours(Double overtimeHours) { this.overtimeHours = overtimeHours; }
    
    public String getAttendanceType() { return attendanceType; }
    public void setAttendanceType(String attendanceType) { this.attendanceType = attendanceType; }
    
    public String getCheckInMethod() { return checkInMethod; }
    public void setCheckInMethod(String checkInMethod) { this.checkInMethod = checkInMethod; }
    
    public String getCheckOutMethod() { return checkOutMethod; }
    public void setCheckOutMethod(String checkOutMethod) { this.checkOutMethod = checkOutMethod; }
    
    public String getCheckInLocation() { return checkInLocation; }
    public void setCheckInLocation(String checkInLocation) { this.checkInLocation = checkInLocation; }
    
    public String getCheckOutLocation() { return checkOutLocation; }
    public void setCheckOutLocation(String checkOutLocation) { this.checkOutLocation = checkOutLocation; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
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
