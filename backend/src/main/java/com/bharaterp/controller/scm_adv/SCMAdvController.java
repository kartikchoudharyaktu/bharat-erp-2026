package com.bharaterp.controller.scm_adv;

import com.bharaterp.model.scm_adv.Procurement;
import com.bharaterp.model.scm_adv.WarehouseManagement;
import com.bharaterp.model.scm_adv.Transportation;
import com.bharaterp.model.scm_adv.ReturnLogistics;
import com.bharaterp.service.scm_adv.SCMAdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/scm-adv")
@CrossOrigin(origins = "*")
public class SCMAdvController {
    
    @Autowired
    private SCMAdvService scmAdvService;
    
    // ===== Procurement APIs =====
    @GetMapping("/procurement")
    public ResponseEntity<List<Procurement>> getAllProcurements() {
        return ResponseEntity.ok(scmAdvService.getAllProcurements());
    }
    
    @PostMapping("/procurement")
    public ResponseEntity<Procurement> createProcurement(@RequestBody Procurement procurement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scmAdvService.createProcurement(procurement));
    }
    
    @PutMapping("/procurement/{id}/approve")
    public ResponseEntity<Procurement> approveProcurement(@PathVariable Long id) {
        return ResponseEntity.ok(scmAdvService.approveProcurement(id));
    }
    
    @PutMapping("/procurement/{id}/order")
    public ResponseEntity<Procurement> orderProcurement(@PathVariable Long id) {
        return ResponseEntity.ok(scmAdvService.orderProcurement(id));
    }
    
    @PutMapping("/procurement/{id}/receive")
    public ResponseEntity<Procurement> receiveProcurement(@PathVariable Long id) {
        return ResponseEntity.ok(scmAdvService.receiveProcurement(id));
    }
    
    // ===== Warehouse APIs =====
    @GetMapping("/warehouse")
    public ResponseEntity<List<WarehouseManagement>> getAllWarehouses() {
        return ResponseEntity.ok(scmAdvService.getAllWarehouses());
    }
    
    @PostMapping("/warehouse")
    public ResponseEntity<WarehouseManagement> createWarehouse(@RequestBody WarehouseManagement warehouse) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scmAdvService.createWarehouse(warehouse));
    }
    
    @PutMapping("/warehouse/{id}/usage")
    public ResponseEntity<WarehouseManagement> updateWarehouseUsage(@PathVariable Long id,
            @RequestParam Double area,
            @RequestParam Integer racks,
            @RequestParam Integer bins) {
        return ResponseEntity.ok(scmAdvService.updateWarehouseUsage(id, area, racks, bins));
    }
    
    // ===== Transportation APIs =====
    @GetMapping("/transport")
    public ResponseEntity<List<Transportation>> getAllTransportations() {
        return ResponseEntity.ok(scmAdvService.getAllTransportations());
    }
    
    @PostMapping("/transport")
    public ResponseEntity<Transportation> createTransportation(@RequestBody Transportation transportation) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scmAdvService.createTransportation(transportation));
    }
    
    @PutMapping("/transport/{id}/start")
    public ResponseEntity<Transportation> startShipment(@PathVariable Long id) {
        return ResponseEntity.ok(scmAdvService.startShipment(id));
    }
    
    @PutMapping("/transport/{id}/deliver")
    public ResponseEntity<Transportation> deliverShipment(@PathVariable Long id) {
        return ResponseEntity.ok(scmAdvService.deliverShipment(id));
    }
    
    // ===== Return APIs =====
    @GetMapping("/returns")
    public ResponseEntity<List<ReturnLogistics>> getAllReturns() {
        return ResponseEntity.ok(scmAdvService.getAllReturns());
    }
    
    @PostMapping("/returns")
    public ResponseEntity<ReturnLogistics> createReturn(@RequestBody ReturnLogistics returnLog) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scmAdvService.createReturn(returnLog));
    }
    
    @PutMapping("/returns/{id}/approve")
    public ResponseEntity<ReturnLogistics> approveReturn(@PathVariable Long id) {
        return ResponseEntity.ok(scmAdvService.approveReturn(id));
    }
    
    @PutMapping("/returns/{id}/refund")
    public ResponseEntity<ReturnLogistics> processRefund(@PathVariable Long id) {
        return ResponseEntity.ok(scmAdvService.processRefund(id));
    }
}
