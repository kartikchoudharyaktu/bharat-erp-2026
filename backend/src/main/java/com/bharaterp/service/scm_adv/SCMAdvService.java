package com.bharaterp.service.scm_adv;

import com.bharaterp.model.scm_adv.Procurement;
import com.bharaterp.model.scm_adv.WarehouseManagement;
import com.bharaterp.model.scm_adv.Transportation;
import com.bharaterp.model.scm_adv.ReturnLogistics;
import com.bharaterp.repository.scm_adv.SCMAdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SCMAdvService {
    
    @Autowired
    private SCMAdvRepository scmAdvRepository;
    
    // ===== Procurement =====
    public List<Procurement> getAllProcurements() {
        return scmAdvRepository.findAll();
    }
    
    public Procurement createProcurement(Procurement procurement) {
        if (procurement.getProcurementNumber() == null) {
            procurement.setProcurementNumber("PROC-" + System.currentTimeMillis());
        }
        return scmAdvRepository.save(procurement);
    }
    
    public Procurement approveProcurement(Long id) {
        Procurement procurement = scmAdvRepository.findById(id).orElseThrow();
        procurement.setStatus("APPROVED");
        procurement.setApprovedDate(LocalDateTime.now());
        return scmAdvRepository.save(procurement);
    }
    
    public Procurement orderProcurement(Long id) {
        Procurement procurement = scmAdvRepository.findById(id).orElseThrow();
        procurement.setStatus("ORDERED");
        procurement.setOrderDate(LocalDateTime.now());
        return scmAdvRepository.save(procurement);
    }
    
    public Procurement receiveProcurement(Long id) {
        Procurement procurement = scmAdvRepository.findById(id).orElseThrow();
        procurement.setStatus("RECEIVED");
        procurement.setReceivedDate(LocalDateTime.now());
        return scmAdvRepository.save(procurement);
    }
    
    // ===== Warehouse Management =====
    public List<WarehouseManagement> getAllWarehouses() {
        return scmAdvRepository.findAll();
    }
    
    public WarehouseManagement createWarehouse(WarehouseManagement warehouse) {
        if (warehouse.getWarehouseCode() == null) {
            warehouse.setWarehouseCode("WH-" + System.currentTimeMillis());
        }
        return scmAdvRepository.save(warehouse);
    }
    
    public WarehouseManagement updateWarehouseUsage(Long id, Double area, Integer racks, Integer bins) {
        WarehouseManagement warehouse = scmAdvRepository.findById(id).orElseThrow();
        warehouse.setUsedArea(area);
        warehouse.setUsedRacks(racks);
        warehouse.setUsedBins(bins);
        warehouse.setAvailableArea(warehouse.getTotalArea() - area);
        warehouse.setAvailableRacks(warehouse.getTotalRacks() - racks);
        warehouse.setAvailableBins(warehouse.getTotalBins() - bins);
        return scmAdvRepository.save(warehouse);
    }
    
    // ===== Transportation =====
    public List<Transportation> getAllTransportations() {
        return scmAdvRepository.findAll();
    }
    
    public Transportation createTransportation(Transportation transportation) {
        if (transportation.getShipmentNumber() == null) {
            transportation.setShipmentNumber("SHP-" + System.currentTimeMillis());
        }
        return scmAdvRepository.save(transportation);
    }
    
    public Transportation startShipment(Long id) {
        Transportation transportation = scmAdvRepository.findById(id).orElseThrow();
        transportation.setStatus("IN_TRANSIT");
        transportation.setActualDeparture(LocalDateTime.now());
        return scmAdvRepository.save(transportation);
    }
    
    public Transportation deliverShipment(Long id) {
        Transportation transportation = scmAdvRepository.findById(id).orElseThrow();
        transportation.setStatus("DELIVERED");
        transportation.setActualArrival(LocalDateTime.now());
        return scmAdvRepository.save(transportation);
    }
    
    // ===== Return Logistics =====
    public List<ReturnLogistics> getAllReturns() {
        return scmAdvRepository.findAll();
    }
    
    public ReturnLogistics createReturn(ReturnLogistics returnLog) {
        if (returnLog.getReturnNumber() == null) {
            returnLog.setReturnNumber("RET-" + System.currentTimeMillis());
        }
        return scmAdvRepository.save(returnLog);
    }
    
    public ReturnLogistics approveReturn(Long id) {
        ReturnLogistics returnLog = scmAdvRepository.findById(id).orElseThrow();
        returnLog.setApprovalStatus("APPROVED");
        returnLog.setReturnStatus("APPROVED");
        returnLog.setApprovedDate(LocalDateTime.now());
        return scmAdvRepository.save(returnLog);
    }
    
    public ReturnLogistics processRefund(Long id) {
        ReturnLogistics returnLog = scmAdvRepository.findById(id).orElseThrow();
        returnLog.setReturnStatus("REFUNDED");
        returnLog.setNetRefund(returnLog.getRefundAmount().subtract(returnLog.getRestockingFee()));
        return scmAdvRepository.save(returnLog);
    }
}
