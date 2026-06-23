package com.bharaterp.dto;

public class CustomerDTO {
    private Long id;
    private String customerName;
    private String email;
    private String phone;
    private String address;
    private String gstNo;
    private String panNo;
    private String state;
    private String city;
    private String pincode;
    private String country;
    private String customerType;
    private Double creditLimit;
    private String status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getGstNo() { return gstNo; }
    public void setGstNo(String gstNo) { this.gstNo = gstNo; }
    public String getPanNo() { return panNo; }
    public void setPanNo(String panNo) { this.panNo = panNo; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getCustomerType() { return customerType; }
    public void setCustomerType(String customerType) { this.customerType = customerType; }
    public Double getCreditLimit() { return creditLimit; }
    public void setCreditLimit(Double creditLimit) { this.creditLimit = creditLimit; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
