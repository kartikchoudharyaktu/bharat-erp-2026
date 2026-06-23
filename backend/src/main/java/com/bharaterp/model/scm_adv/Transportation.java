package com.bharaterp.model.scm_adv;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Entity
@Table(name = "transportation")
public class Transportation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String shipmentNumber;
    
    @Column(nullable = false)
    private String shipmentType; // INBOUND, OUTBOUND, INTERNAL
    
    @Column(nullable = false)
    private String transportMode; // ROAD, RAIL, AIR, SEA, PIPELINE
    
    @Column(nullable = false)
    private String sourceLocation;
    
    @Column(nullable = false)
    private String destinationLocation;
    
    private String carrierName;
    private String carrierCode;
    
    private String vehicleNumber;
    private String driverName;
    private String driverContact;
    
    private Double distance = 0.0;
    private Double estimatedHours = 0.0;
    private Double actualHours = 0.0;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal freightCost = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal fuelCost = BigDecimal.ZERO;
    
    @Column(precision = 19, scale = 2)
    private BigDecimal totalCost = BigDecimal.ZERO;
    
    private String status = "PLANNED"; // PLANNED, IN_TRANSIT, DELIVERED, DELAYED, CANCELLED
    
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private LocalDateTime actualDeparture;
    private LocalDateTime actualArrival;
    
    private String trackingNumber;
    private String trackingUrl;
    
    private String route;
    private String stops; // JSON array of stops
    
    private String notes;
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getShipmentNumber() { return shipmentNumber; }
    public void setShipmentNumber(String shipmentNumber) { this.shipmentNumber = shipmentNumber; }
    
    public String getShipmentType() { return shipmentType; }
    public void setShipmentType(String shipmentType) { this.shipmentType = shipmentType; }
    
    public String getTransportMode() { return transportMode; }
    public void setTransportMode(String transportMode) { this.transportMode = transportMode; }
    
    public String getSourceLocation() { return sourceLocation; }
    public void setSourceLocation(String sourceLocation) { this.sourceLocation = sourceLocation; }
    
    public String getDestinationLocation() { return destinationLocation; }
    public void setDestinationLocation(String destinationLocation) { this.destinationLocation = destinationLocation; }
    
    public String getCarrierName() { return carrierName; }
    public void setCarrierName(String carrierName) { this.carrierName = carrierName; }
    
    public String getCarrierCode() { return carrierCode; }
    public void setCarrierCode(String carrierCode) { this.carrierCode = carrierCode; }
    
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    
    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }
    
    public String getDriverContact() { return driverContact; }
    public void setDriverContact(String driverContact) { this.driverContact = driverContact; }
    
    public Double getDistance() { return distance; }
    public void setDistance(Double distance) { this.distance = distance; }
    
    public Double getEstimatedHours() { return estimatedHours; }
    public void setEstimatedHours(Double estimatedHours) { this.estimatedHours = estimatedHours; }
    
    public Double getActualHours() { return actualHours; }
    public void setActualHours(Double actualHours) { this.actualHours = actualHours; }
    
    public BigDecimal getFreightCost() { return freightCost; }
    public void setFreightCost(BigDecimal freightCost) { this.freightCost = freightCost; }
    
    public BigDecimal getFuelCost() { return fuelCost; }
    public void setFuelCost(BigDecimal fuelCost) { this.fuelCost = fuelCost; }
    
    public BigDecimal getTotalCost() { return totalCost; }
    public void setTotalCost(BigDecimal totalCost) { this.totalCost = totalCost; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }
    
    public LocalDateTime getActualDeparture() { return actualDeparture; }
    public void setActualDeparture(LocalDateTime actualDeparture) { this.actualDeparture = actualDeparture; }
    
    public LocalDateTime getActualArrival() { return actualArrival; }
    public void setActualArrival(LocalDateTime actualArrival) { this.actualArrival = actualArrival; }
    
    public String getTrackingNumber() { return trackingNumber; }
    public void setTrackingNumber(String trackingNumber) { this.trackingNumber = trackingNumber; }
    
    public String getTrackingUrl() { return trackingUrl; }
    public void setTrackingUrl(String trackingUrl) { this.trackingUrl = trackingUrl; }
    
    public String getRoute() { return route; }
    public void setRoute(String route) { this.route = route; }
    
    public String getStops() { return stops; }
    public void setStops(String stops) { this.stops = stops; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
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
        if (shipmentNumber == null || shipmentNumber.isEmpty()) {
            shipmentNumber = "SHP-" + System.currentTimeMillis();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
