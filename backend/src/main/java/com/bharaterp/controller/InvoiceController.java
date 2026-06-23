package com.bharaterp.controller;

import com.bharaterp.model.Invoice;
import com.bharaterp.model.InvoiceItem;
import com.bharaterp.repository.InvoiceRepository;
import com.bharaterp.repository.InvoiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*")
public class InvoiceController {
    
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private InvoiceItemRepository invoiceItemRepository;
    
    private static final String[] UNITS = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] TEENS = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    @GetMapping
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getInvoiceById(@PathVariable Long id) {
        Optional<Invoice> invoiceOpt = invoiceRepository.findById(id);
        if (!invoiceOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        List<InvoiceItem> items = invoiceItemRepository.findByInvoiceId(id);
        Map<String, Object> response = new HashMap<>();
        response.put("invoice", invoiceOpt.get());
        response.put("items", items);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<?> createInvoice(@RequestBody Map<String, Object> request) {
        try {
            // Create invoice
            Invoice invoice = new Invoice();
            invoice.setInvoiceNumber(generateInvoiceNumber());
            invoice.setInvoiceDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            invoice.setCustomerId(((Number) request.get("customerId")).longValue());
            invoice.setCustomerName((String) request.get("customerName"));
            invoice.setCustomerGstin((String) request.get("customerGstin"));
            invoice.setCustomerAddress((String) request.get("customerAddress"));
            invoice.setCreatedBy("admin");
            invoice.setStatus("DRAFT");
            invoice.setPosted(0);
            invoice.setCreatedAt(LocalDateTime.now());
            
            // Calculate totals
            List<Map<String, Object>> items = (List<Map<String, Object>>) request.get("items");
            double taxable = 0;
            double totalGst = 0;
            double cgst = 0;
            double sgst = 0;
            double igst = 0;
            
            for (Map<String, Object> item : items) {
                double amount = ((Number) item.get("amount")).doubleValue();
                double gstRate = ((Number) item.get("gstRate")).doubleValue();
                double gstAmount = amount * gstRate / 100;
                taxable += amount;
                totalGst += gstAmount;
                cgst += gstAmount / 2;
                sgst += gstAmount / 2;
            }
            
            invoice.setTotalAmount(taxable + totalGst);
            invoice.setTaxableAmount(taxable);
            invoice.setCgst(cgst);
            invoice.setSgst(sgst);
            invoice.setIgst(igst);
            invoice.setTotalGst(totalGst);
            invoice.setGrandTotal(taxable + totalGst);
            invoice.setAmountInWords(convertToWords(taxable + totalGst));
            
            Invoice savedInvoice = invoiceRepository.save(invoice);
            
            // Save invoice items
            for (Map<String, Object> item : items) {
                InvoiceItem invItem = new InvoiceItem();
                invItem.setInvoiceId(savedInvoice.getId());
                invItem.setProductName((String) item.get("name"));
                invItem.setHsn((String) item.get("hsn"));
                invItem.setQuantity(((Number) item.get("qty")).doubleValue());
                invItem.setRate(((Number) item.get("rate")).doubleValue());
                invItem.setAmount(((Number) item.get("amount")).doubleValue());
                invItem.setGstRate(((Number) item.get("gstRate")).doubleValue());
                invItem.setGstAmount(((Number) item.get("gstAmount")).doubleValue());
                invItem.setTotal(((Number) item.get("total")).doubleValue());
                invoiceItemRepository.save(invItem);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invoice created successfully!");
            response.put("invoiceNumber", savedInvoice.getInvoiceNumber());
            response.put("id", savedInvoice.getId());
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        try {
            Optional<Invoice> invoiceOpt = invoiceRepository.findById(id);
            if (!invoiceOpt.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            
            Invoice invoice = invoiceOpt.get();
            invoice.setStatus((String) request.getOrDefault("status", invoice.getStatus()));
            invoice.setPosted(((Number) request.getOrDefault("posted", invoice.getPosted())).intValue());
            
            Invoice updated = invoiceRepository.save(invoice);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invoice updated successfully!");
            response.put("invoice", updated);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long id) {
        try {
            if (!invoiceRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            invoiceItemRepository.deleteByInvoiceId(id);
            invoiceRepository.deleteById(id);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Invoice deleted successfully!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    private String generateInvoiceNumber() {
        String prefix = "INV-2026-";
        List<Invoice> invoices = invoiceRepository.findAll();
        int count = invoices.size() + 1;
        return prefix + String.format("%04d", count);
    }
    
    private String convertToWords(double amount) {
        int rupees = (int) amount;
        int paise = (int) Math.round((amount - rupees) * 100);
        
        String words = convertNumberToWords(rupees) + " Rupees";
        if (paise > 0) {
            words += " and " + convertNumberToWords(paise) + " Paise";
        }
        return words + " Only";
    }
    
    private String convertNumberToWords(int num) {
        if (num == 0) return "Zero";
        if (num < 10) return UNITS[num];
        if (num < 20) return TEENS[num - 10];
        if (num < 100) return TENS[num / 10] + (num % 10 > 0 ? " " + UNITS[num % 10] : "");
        if (num < 1000) return UNITS[num / 100] + " Hundred" + (num % 100 > 0 ? " " + convertNumberToWords(num % 100) : "");
        if (num < 100000) return convertNumberToWords(num / 1000) + " Thousand" + (num % 1000 > 0 ? " " + convertNumberToWords(num % 1000) : "");
        if (num < 10000000) return convertNumberToWords(num / 100000) + " Lakh" + (num % 100000 > 0 ? " " + convertNumberToWords(num % 100000) : "");
        return convertNumberToWords(num / 10000000) + " Crore" + (num % 10000000 > 0 ? " " + convertNumberToWords(num % 10000000) : "");
    }
}