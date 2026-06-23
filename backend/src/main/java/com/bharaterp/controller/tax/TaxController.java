package com.bharaterp.controller.tax;

import com.bharaterp.model.tax.GSTInvoice;
import com.bharaterp.service.tax.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tax")
@CrossOrigin(origins = "*")
public class TaxController {
    
    @Autowired
    private TaxService taxService;
    
    // GST Invoice APIs
    @GetMapping("/invoices")
    public ResponseEntity<List<GSTInvoice>> getAllInvoices() {
        return ResponseEntity.ok(taxService.getAllGSTInvoices());
    }
    
    @GetMapping("/invoices/{id}")
    public ResponseEntity<GSTInvoice> getInvoiceById(@PathVariable Long id) {
        return taxService.getGSTInvoiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/invoices/number/{number}")
    public ResponseEntity<GSTInvoice> getInvoiceByNumber(@PathVariable String number) {
        return taxService.getGSTInvoiceByNumber(number)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/invoices")
    public ResponseEntity<GSTInvoice> createInvoice(@RequestBody GSTInvoice invoice) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taxService.createGSTInvoice(invoice));
    }
    
    @PutMapping("/invoices/{id}")
    public ResponseEntity<GSTInvoice> updateInvoice(@PathVariable Long id, @RequestBody GSTInvoice invoice) {
        return ResponseEntity.ok(taxService.updateGSTInvoice(id, invoice));
    }
    
    @PutMapping("/invoices/{id}/submit")
    public ResponseEntity<GSTInvoice> submitInvoice(@PathVariable Long id) {
        return ResponseEntity.ok(taxService.submitGSTInvoice(id));
    }
    
    @PutMapping("/invoices/{id}/cancel")
    public ResponseEntity<GSTInvoice> cancelInvoice(@PathVariable Long id) {
        return ResponseEntity.ok(taxService.cancelGSTInvoice(id));
    }
    
    @DeleteMapping("/invoices/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long id) {
        taxService.deleteGSTInvoice(id);
        return ResponseEntity.noContent().build();
    }
    
    // Tax Calculation APIs
    @GetMapping("/calculate/cgst")
    public ResponseEntity<BigDecimal> calculateCGST(
            @RequestParam BigDecimal amount,
            @RequestParam BigDecimal rate) {
        return ResponseEntity.ok(taxService.calculateCGST(amount, rate));
    }
    
    @GetMapping("/calculate/sgst")
    public ResponseEntity<BigDecimal> calculateSGST(
            @RequestParam BigDecimal amount,
            @RequestParam BigDecimal rate) {
        return ResponseEntity.ok(taxService.calculateSGST(amount, rate));
    }
    
    @GetMapping("/calculate/igst")
    public ResponseEntity<BigDecimal> calculateIGST(
            @RequestParam BigDecimal amount,
            @RequestParam BigDecimal rate) {
        return ResponseEntity.ok(taxService.calculateIGST(amount, rate));
    }
    
    @GetMapping("/calculate/total-gst")
    public ResponseEntity<BigDecimal> calculateTotalGST(
            @RequestParam BigDecimal cgst,
            @RequestParam BigDecimal sgst,
            @RequestParam BigDecimal igst) {
        return ResponseEntity.ok(taxService.calculateTotalGST(cgst, sgst, igst));
    }
    
    @GetMapping("/calculate/grand-total")
    public ResponseEntity<BigDecimal> calculateGrandTotal(
            @RequestParam BigDecimal taxableAmount,
            @RequestParam BigDecimal totalGST) {
        return ResponseEntity.ok(taxService.calculateGrandTotal(taxableAmount, totalGST));
    }
    
    // GST Returns APIs
    @GetMapping("/returns/sales")
    public ResponseEntity<BigDecimal> getTotalSales(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(taxService.getTotalSales(start, end));
    }
    
    @GetMapping("/returns/gst")
    public ResponseEntity<BigDecimal> getTotalGST(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(taxService.getTotalGST(start, end));
    }
}
