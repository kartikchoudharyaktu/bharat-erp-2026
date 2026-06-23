package com.bharaterp.controller.sales;

import com.bharaterp.model.sales.SalesOrder;
import com.bharaterp.service.sales.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "*")
public class SalesController {
    
    @Autowired
    private SalesService salesService;
    
    @GetMapping("/orders")
    public ResponseEntity<List<SalesOrder>> getAllOrders() {
        return ResponseEntity.ok(salesService.getAllOrders());
    }
    
    @GetMapping("/orders/{id}")
    public ResponseEntity<SalesOrder> getOrderById(@PathVariable Long id) {
        return salesService.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/orders")
    public ResponseEntity<SalesOrder> createOrder(@RequestBody SalesOrder order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salesService.createOrder(order));
    }
    
    @PutMapping("/orders/{id}")
    public ResponseEntity<SalesOrder> updateOrder(@PathVariable Long id, @RequestBody SalesOrder order) {
        return ResponseEntity.ok(salesService.updateOrder(id, order));
    }
    
    @PutMapping("/orders/{id}/status")
    public ResponseEntity<SalesOrder> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(salesService.updateStatus(id, status));
    }
    
    @PutMapping("/orders/{id}/payment")
    public ResponseEntity<SalesOrder> updatePaymentStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(salesService.updatePaymentStatus(id, status));
    }
    
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        salesService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/reports/total-sales")
    public ResponseEntity<BigDecimal> getTotalSales(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(salesService.getTotalSales(start, end));
    }
}
