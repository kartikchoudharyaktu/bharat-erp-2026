package com.bharaterp.controller.manufacturing;

import com.bharaterp.model.manufacturing.BillOfMaterials;
import com.bharaterp.model.manufacturing.ProductionOrder;
import com.bharaterp.service.manufacturing.ManufacturingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/manufacturing")
@CrossOrigin(origins = "*")
public class ManufacturingController {
    
    @Autowired
    private ManufacturingService manufacturingService;
    
    // ===== BOM APIs =====
    @GetMapping("/boms")
    public ResponseEntity<List<BillOfMaterials>> getAllBOMs() {
        return ResponseEntity.ok(manufacturingService.getAllBOMs());
    }
    
    @GetMapping("/boms/{id}")
    public ResponseEntity<BillOfMaterials> getBOMById(@PathVariable Long id) {
        return manufacturingService.getBOMById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/boms/code/{code}")
    public ResponseEntity<BillOfMaterials> getBOMByCode(@PathVariable String code) {
        return manufacturingService.getBOMByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/boms")
    public ResponseEntity<BillOfMaterials> createBOM(@RequestBody BillOfMaterials bom) {
        return ResponseEntity.status(HttpStatus.CREATED).body(manufacturingService.createBOM(bom));
    }
    
    @PutMapping("/boms/{id}")
    public ResponseEntity<BillOfMaterials> updateBOM(@PathVariable Long id, @RequestBody BillOfMaterials bom) {
        return ResponseEntity.ok(manufacturingService.updateBOM(id, bom));
    }
    
    @DeleteMapping("/boms/{id}")
    public ResponseEntity<Void> deleteBOM(@PathVariable Long id) {
        manufacturingService.deleteBOM(id);
        return ResponseEntity.noContent().build();
    }
    
    // ===== Production Order APIs =====
    @GetMapping("/orders")
    public ResponseEntity<List<ProductionOrder>> getAllOrders() {
        return ResponseEntity.ok(manufacturingService.getAllProductionOrders());
    }
    
    @GetMapping("/orders/{id}")
    public ResponseEntity<ProductionOrder> getOrderById(@PathVariable Long id) {
        return manufacturingService.getProductionOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/orders/number/{number}")
    public ResponseEntity<ProductionOrder> getOrderByNumber(@PathVariable String number) {
        return manufacturingService.getProductionOrderByNumber(number)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/orders")
    public ResponseEntity<ProductionOrder> createOrder(@RequestBody ProductionOrder order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(manufacturingService.createProductionOrder(order));
    }
    
    @PutMapping("/orders/{id}")
    public ResponseEntity<ProductionOrder> updateOrder(@PathVariable Long id, @RequestBody ProductionOrder order) {
        return ResponseEntity.ok(manufacturingService.updateProductionOrder(id, order));
    }
    
    @PutMapping("/orders/{id}/start")
    public ResponseEntity<ProductionOrder> startProduction(@PathVariable Long id) {
        return ResponseEntity.ok(manufacturingService.startProduction(id));
    }
    
    @PutMapping("/orders/{id}/complete")
    public ResponseEntity<ProductionOrder> completeProduction(@PathVariable Long id) {
        return ResponseEntity.ok(manufacturingService.completeProduction(id));
    }
    
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        manufacturingService.deleteProductionOrder(id);
        return ResponseEntity.noContent().build();
    }
    
    // ===== Report APIs =====
    @GetMapping("/reports/status/{status}")
    public ResponseEntity<List<ProductionOrder>> getOrdersByStatus(@PathVariable String status) {
        return ResponseEntity.ok(manufacturingService.getOrdersByStatus(status));
    }
    
    @GetMapping("/reports/date-range")
    public ResponseEntity<List<ProductionOrder>> getOrdersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return ResponseEntity.ok(manufacturingService.getOrdersByDateRange(start, end));
    }
}
