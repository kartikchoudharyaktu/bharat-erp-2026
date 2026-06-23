package com.bharaterp.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class ReportDTO {
    private String reportType; private String reportName; private LocalDateTime generatedDate;
    private Map<String, Object> data; private String fileFormat; private String filePath;
    private Long generatedBy; private String status;

    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }
    public String getReportName() { return reportName; }
    public void setReportName(String reportName) { this.reportName = reportName; }
    public LocalDateTime getGeneratedDate() { return generatedDate; }
    public void setGeneratedDate(LocalDateTime generatedDate) { this.generatedDate = generatedDate; }
    public Map<String, Object> getData() { return data; }
    public void setData(Map<String, Object> data) { this.data = data; }
    public String getFileFormat() { return fileFormat; }
    public void setFileFormat(String fileFormat) { this.fileFormat = fileFormat; }
    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }
    public Long getGeneratedBy() { return generatedBy; }
    public void setGeneratedBy(Long generatedBy) { this.generatedBy = generatedBy; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
