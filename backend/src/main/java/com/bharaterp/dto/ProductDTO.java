package com.bharaterp.dto;

public class ProductDTO {
    private Long id; private String productName; private String productCode; private String category;
    private String subCategory; private String brand; private String unit; private Double price;
    private Double cost; private Double mrp; private Integer quantity; private String description;
    private String hsnCode; private Double gstRate; private String status; private String imageUrl;
    private Double minStockLevel; private Double maxStockLevel;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getSubCategory() { return subCategory; }
    public void setSubCategory(String subCategory) { this.subCategory = subCategory; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public Double getCost() { return cost; }
    public void setCost(Double cost) { this.cost = cost; }
    public Double getMrp() { return mrp; }
    public void setMrp(Double mrp) { this.mrp = mrp; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getHsnCode() { return hsnCode; }
    public void setHsnCode(String hsnCode) { this.hsnCode = hsnCode; }
    public Double getGstRate() { return gstRate; }
    public void setGstRate(Double gstRate) { this.gstRate = gstRate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public Double getMinStockLevel() { return minStockLevel; }
    public void setMinStockLevel(Double minStockLevel) { this.minStockLevel = minStockLevel; }
    public Double getMaxStockLevel() { return maxStockLevel; }
    public void setMaxStockLevel(Double maxStockLevel) { this.maxStockLevel = maxStockLevel; }
}
