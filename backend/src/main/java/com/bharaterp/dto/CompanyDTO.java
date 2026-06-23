package com.bharaterp.dto;

import java.time.LocalDateTime;

public class CompanyDTO {
    private Long id; private String companyName; private String companyCode; private String gstNo;
    private String panNo; private String cinNo; private String address; private String city;
    private String state; private String pincode; private String country; private String phone;
    private String email; private String website; private String logo; private String currency;
    private String timezone; private String fiscalYearStart; private String fiscalYearEnd;
    private String status; private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }
    public String getCompanyCode() { return companyCode; }
    public void setCompanyCode(String companyCode) { this.companyCode = companyCode; }
    public String getGstNo() { return gstNo; }
    public void setGstNo(String gstNo) { this.gstNo = gstNo; }
    public String getPanNo() { return panNo; }
    public void setPanNo(String panNo) { this.panNo = panNo; }
    public String getCinNo() { return cinNo; }
    public void setCinNo(String cinNo) { this.cinNo = cinNo; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }
    public String getFiscalYearStart() { return fiscalYearStart; }
    public void setFiscalYearStart(String fiscalYearStart) { this.fiscalYearStart = fiscalYearStart; }
    public String getFiscalYearEnd() { return fiscalYearEnd; }
    public void setFiscalYearEnd(String fiscalYearEnd) { this.fiscalYearEnd = fiscalYearEnd; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
