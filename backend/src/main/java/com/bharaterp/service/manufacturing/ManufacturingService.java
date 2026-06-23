package com.bharaterp.service.manufacturing;

import com.bharaterp.model.manufacturing.BillOfMaterials;
import com.bharaterp.model.manufacturing.ProductionOrder;
import com.bharaterp.model.manufacturing.BOMItem;
import com.bharaterp.model.manufacturing.QualityCheck;
import com.bharaterp.repository.manufacturing.ManufacturingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Service
public class ManufacturingService {
    
    @Autowired
    private ManufacturingRepository manufacturingRepository;
    
    // BOM Operations
    public List<BillOfMaterials> getAllBOMs() {
        return manufacturingRepository.findAll();
    }
    
    public Optional<BillOfMaterials> getBOMById(Long id) {
        return manufacturingRepository.findById(id);
    }
    
    public Optional<BillOfMaterials> getBOMByCode(String code) {
        return manufacturingRepository.findByBomCode(code);
    }
    
    public BillOfMaterials createBOM(BillOfMaterials bom) {
        if (bom.getBomCode() == null || bom.getBomCode().isEmpty()) {
            bom.setBomCode("BOM-" + System.currentTimeMillis());
        }
        return manufacturingRepository.save(bom);
    }
    
    public BillOfMaterials updateBOM(Long id, BillOfMaterials bomDetails) {
        BillOfMaterials bom = manufacturingRepository.findById(id).orElseThrow();
        bom.setBomName(bomDetails.getBomName());
        bom.setProductId(bomDetails.getProductId());
        bom.setProductName(bomDetails.getProductName());
        bom.setProductCode(bomDetails.getProductCode());
        bom.setVersion(bomDetails.getVersion());
        bom.setQuantity(bomDetails.getQuantity());
        bom.setUnit(bomDetails.getUnit());
        bom.setTotalCost(bomDetails.getTotalCost());
        bom.setLaborCost(bomDetails.getLaborCost());
        bom.setOverheadCost(bomDetails.getOverheadCost());
        bom.setStatus(bomDetails.getStatus());
        bom.setDescription(bomDetails.getDescription());
        return manufacturingRepository.save(bom);
    }
    
    public void deleteBOM(Long id) {
        manufacturingRepository.deleteById(id);
    }
    
    // Production Order Operations
    public List<ProductionOrder> getAllProductionOrders() {
        return manufacturingRepository.findAll();
    }
    
    public Optional<ProductionOrder> getProductionOrderById(Long id) {
        return manufacturingRepository.findById(id);
    }
    
    public Optional<ProductionOrder> getProductionOrderByNumber(String number) {
        return manufacturingRepository.findByOrderNumber(number);
    }
    
    public ProductionOrder createProductionOrder(ProductionOrder order) {
        if (order.getOrderNumber() == null || order.getOrderNumber().isEmpty()) {
            order.setOrderNumber("PO-" + System.currentTimeMillis());
        }
        return manufacturingRepository.save(order);
    }
    
    public ProductionOrder updateProductionOrder(Long id, ProductionOrder orderDetails) {
        ProductionOrder order = manufacturingRepository.findById(id).orElseThrow();
        order.setProductId(orderDetails.getProductId());
        order.setProductName(orderDetails.getProductName());
        order.setProductCode(orderDetails.getProductCode());
        order.setQuantity(orderDetails.getQuantity());
        order.setCompletedQuantity(orderDetails.getCompletedQuantity());
        order.setRejectedQuantity(orderDetails.getRejectedQuantity());
        order.setStartDate(orderDetails.getStartDate());
        order.setEndDate(orderDetails.getEndDate());
        order.setStatus(orderDetails.getStatus());
        order.setPriority(orderDetails.getPriority());
        order.setBomId(orderDetails.getBomId());
        order.setShift(orderDetails.getShift());
        order.setMachineNumber(orderDetails.getMachineNumber());
        order.setOperatorName(orderDetails.getOperatorName());
        order.setInstructions(orderDetails.getInstructions());
        return manufacturingRepository.save(order);
    }
    
    public ProductionOrder startProduction(Long id) {
        ProductionOrder order = manufacturingRepository.findById(id).orElseThrow();
        order.setStatus("IN_PROGRESS");
        order.setActualStartDate(LocalDate.now());
        return manufacturingRepository.save(order);
    }
    
    public ProductionOrder completeProduction(Long id) {
        ProductionOrder order = manufacturingRepository.findById(id).orElseThrow();
        order.setStatus("COMPLETED");
        order.setActualEndDate(LocalDate.now());
        order.setCompletedQuantity(order.getQuantity());
        return manufacturingRepository.save(order);
    }
    
    public void deleteProductionOrder(Long id) {
        manufacturingRepository.deleteById(id);
    }
    
    // Reports
    public List<ProductionOrder> getOrdersByStatus(String status) {
        return manufacturingRepository.findByStatus(status);
    }
    
    public List<ProductionOrder> getOrdersByDateRange(LocalDate start, LocalDate end) {
        return manufacturingRepository.findByStartDateBetween(start, end);
    }
    
    // BOM Items Operations
    // (Would use BOMItemRepository)
    
    // Quality Check Operations
    // (Would use QualityCheckRepository)
}
