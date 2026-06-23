package com.bharaterp.controller.sales_adv;

import com.bharaterp.model.sales_adv.CRM;
import com.bharaterp.model.sales_adv.ECommerceIntegration;
import com.bharaterp.model.sales_adv.MarketingAutomation;
import com.bharaterp.model.sales_adv.AfterSalesService;
import com.bharaterp.service.sales_adv.SalesAdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sales-adv")
@CrossOrigin(origins = "*")
public class SalesAdvController {
    
    @Autowired
    private SalesAdvService salesAdvService;
    
    // ===== CRM APIs =====
    @GetMapping("/crm")
    public ResponseEntity<List<CRM>> getAllCRMInteractions() {
        return ResponseEntity.ok(salesAdvService.getAllCRMInteractions());
    }
    
    @PostMapping("/crm")
    public ResponseEntity<CRM> createCRMInteraction(@RequestBody CRM crm) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salesAdvService.createCRMInteraction(crm));
    }
    
    @PutMapping("/crm/{id}/status")
    public ResponseEntity<CRM> updateCRMStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(salesAdvService.updateCRMStatus(id, status));
    }
    
    // ===== E-Commerce APIs =====
    @GetMapping("/ecommerce")
    public ResponseEntity<List<ECommerceIntegration>> getAllECommerceOrders() {
        return ResponseEntity.ok(salesAdvService.getAllECommerceOrders());
    }
    
    @PostMapping("/ecommerce")
    public ResponseEntity<ECommerceIntegration> createECommerceOrder(@RequestBody ECommerceIntegration order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salesAdvService.createECommerceOrder(order));
    }
    
    @PutMapping("/ecommerce/{id}/sync")
    public ResponseEntity<ECommerceIntegration> syncOrder(@PathVariable Long id) {
        return ResponseEntity.ok(salesAdvService.syncOrder(id));
    }
    
    // ===== Marketing APIs =====
    @GetMapping("/marketing")
    public ResponseEntity<List<MarketingAutomation>> getAllCampaigns() {
        return ResponseEntity.ok(salesAdvService.getAllCampaigns());
    }
    
    @PostMapping("/marketing")
    public ResponseEntity<MarketingAutomation> createCampaign(@RequestBody MarketingAutomation campaign) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salesAdvService.createCampaign(campaign));
    }
    
    @PutMapping("/marketing/{id}/schedule")
    public ResponseEntity<MarketingAutomation> scheduleCampaign(@PathVariable Long id) {
        return ResponseEntity.ok(salesAdvService.scheduleCampaign(id));
    }
    
    @PutMapping("/marketing/{id}/send")
    public ResponseEntity<MarketingAutomation> sendCampaign(@PathVariable Long id) {
        return ResponseEntity.ok(salesAdvService.sendCampaign(id));
    }
    
    // ===== After-Sales APIs =====
    @GetMapping("/service")
    public ResponseEntity<List<AfterSalesService>> getAllServices() {
        return ResponseEntity.ok(salesAdvService.getAllServices());
    }
    
    @PostMapping("/service")
    public ResponseEntity<AfterSalesService> createService(@RequestBody AfterSalesService service) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salesAdvService.createService(service));
    }
    
    @PutMapping("/service/{id}/assign")
    public ResponseEntity<AfterSalesService> assignService(@PathVariable Long id, @RequestParam String assignedTo) {
        return ResponseEntity.ok(salesAdvService.assignService(id, assignedTo));
    }
    
    @PutMapping("/service/{id}/complete")
    public ResponseEntity<AfterSalesService> completeService(@PathVariable Long id) {
        return ResponseEntity.ok(salesAdvService.completeService(id));
    }
}
