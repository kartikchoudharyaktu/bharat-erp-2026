package com.bharaterp.model.ux;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_preferences")
public class UserPreference {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private Long userId;
    
    private String language = "EN"; // EN, HI, etc.
    private String theme = "LIGHT"; // LIGHT, DARK, SYSTEM
    
    private String dashboardLayout; // JSON layout
    private String widgetPreferences; // JSON widgets
    
    private Boolean emailNotifications = true;
    private Boolean pushNotifications = true;
    private Boolean smsNotifications = false;
    
    private String reportPreferences; // JSON report settings
    private String menuPreferences; // JSON menu customization
    
    private String timezone = "Asia/Kolkata";
    private String dateFormat = "DD/MM/YYYY";
    private String timeFormat = "12H";
    
    private Boolean isAccessibilityEnabled = false;
    private String accessibilitySettings; // JSON
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    
    public String getTheme() { return theme; }
    public void setTheme(String theme) { this.theme = theme; }
    
    public String getDashboardLayout() { return dashboardLayout; }
    public void setDashboardLayout(String dashboardLayout) { this.dashboardLayout = dashboardLayout; }
    
    public String getWidgetPreferences() { return widgetPreferences; }
    public void setWidgetPreferences(String widgetPreferences) { this.widgetPreferences = widgetPreferences; }
    
    public Boolean getEmailNotifications() { return emailNotifications; }
    public void setEmailNotifications(Boolean emailNotifications) { this.emailNotifications = emailNotifications; }
    
    public Boolean getPushNotifications() { return pushNotifications; }
    public void setPushNotifications(Boolean pushNotifications) { this.pushNotifications = pushNotifications; }
    
    public Boolean getSmsNotifications() { return smsNotifications; }
    public void setSmsNotifications(Boolean smsNotifications) { this.smsNotifications = smsNotifications; }
    
    public String getReportPreferences() { return reportPreferences; }
    public void setReportPreferences(String reportPreferences) { this.reportPreferences = reportPreferences; }
    
    public String getMenuPreferences() { return menuPreferences; }
    public void setMenuPreferences(String menuPreferences) { this.menuPreferences = menuPreferences; }
    
    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }
    
    public String getDateFormat() { return dateFormat; }
    public void setDateFormat(String dateFormat) { this.dateFormat = dateFormat; }
    
    public String getTimeFormat() { return timeFormat; }
    public void setTimeFormat(String timeFormat) { this.timeFormat = timeFormat; }
    
    public Boolean getIsAccessibilityEnabled() { return isAccessibilityEnabled; }
    public void setIsAccessibilityEnabled(Boolean isAccessibilityEnabled) { this.isAccessibilityEnabled = isAccessibilityEnabled; }
    
    public String getAccessibilitySettings() { return accessibilitySettings; }
    public void setAccessibilitySettings(String accessibilitySettings) { this.accessibilitySettings = accessibilitySettings; }
    
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
