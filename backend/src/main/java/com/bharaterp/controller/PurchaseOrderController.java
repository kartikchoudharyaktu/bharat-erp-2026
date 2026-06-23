package com.bharaterp.controller;

import com.bharaterp.model.PurchaseOrder;
import com.bharaterp.model.POItem;
import com.bharaterp.repository.PurchaseOrderRepository;
import com.bharaterp.repository.POItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/purchase-orders")
@CrossOrigin(origins = "*")
public class PurchaseOrderController {
    
    @Autowired
    private PurchaseOrderRepository poRepository;
    
    @Autowired
    private POItemRepository poItemRepository;
    
    private static final String[] UNITS = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] TEENS = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    
    @GetMapping
    public List<PurchaseOrder> getAll() {
        return poRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        Optional<PurchaseOrder> poOpt = poRepository.findById(id);
        if (!poOpt.isPresent()) return ResponseEntity.notFound().build();
        
        List<POItem> items = poItemRepository.findByPoId(id);
        Map<String, Object> response = new HashMap<>();
        response.put("po", poOpt.get());
        response.put("items", items);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> request) {
        try {
            PurchaseOrder po = new PurchaseOrder();
            po.setPoNumber(generatePONumber());
            po.setPoDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            po.setVendorId(((Number) request.get("vendorId")).longValue());
            po.setVendorName((String) request.get("vendorName"));
            po.setVendorGstin((String) request.get("vendorGstin"));
            po.setDeliveryDate((String) request.get("deliveryDate"));
            po.setStatus("DRAFT");
            po.setCreatedBy("admin");
            po.setCreatedAt(LocalDateTime.now());
            
            List<Map<String, Object>> items = (List<Map<String, Object>>) request.get("items");
            double taxable = 0, totalGst = 0, cgst = 0, sgst = 0, igst = 0;
            
            for (Map<String, Object> item : items) {
                double amount = ((Number) item.get("amount")).doubleValue();
                double gstRate = ((Number) item.get("gstRate")).doubleValue();
                double gstAmount = amount * gstRate / 100;
                taxable += amount;
                totalGst += gstAmount;
                cgst += gstAmount / 2;
                sgst += gstAmount / 2;
            }
            
            po.setTotalAmount(taxable + totalGst);
            po.setTaxableAmount(taxable);
            po.setCgst(cgst);
            po.setSgst(sgst);
            po.setIgst(igst);
            po.setTotalGst(totalGst);
            po.setGrandTotal(taxable + totalGst);
            po.setAmountInWords(convertToWords(taxable + totalGst));
            
            PurchaseOrder saved = poRepository.save(po);
            
            for (Map<String, Object> item : items) {
                POItem poItem = new POItem();
                poItem.setPoId(saved.getId());
                poItem.setProductName((String) item.get("name"));
                poItem.setHsn((String) item.get("hsn"));
                poItem.setQuantity(((Number) item.get("qty")).doubleValue());
                poItem.setRate(((Number) item.get("rate")).doubleValue());
                poItem.setAmount(((Number) item.get("amount")).doubleValue());
                poItem.setGstRate(((Number) item.get("gstRate")).doubleValue());
                poItem.setGstAmount(((Number) item.get("gstAmount")).doubleValue());
                poItem.setTotal(((Number) item.get("total")).doubleValue());
                poItemRepository.save(poItem);
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "PO created successfully!");
            response.put("poNumber", saved.getPoNumber());
            response.put("id", saved.getId());
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        try {
            Optional<PurchaseOrder> poOpt = poRepository.findById(id);
            if (!poOpt.isPresent()) return ResponseEntity.notFound().build();
            
            PurchaseOrder po = poOpt.get();
            po.setStatus((String) request.getOrDefault("status", po.getStatus()));
            PurchaseOrder updated = poRepository.save(po);
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "PO updated!");
            response.put("po", updated);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        poItemRepository.deleteByPoId(id);
        poRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
    
    private String generatePONumber() {
        String prefix = "PO-2026-";
        List<PurchaseOrder> pos = poRepository.findAll();
        int count = pos.size() + 1;
        return prefix + String.format("%04d", count);
    }
    
    private String convertToWords(double amount) {
        int rupees = (int) amount;
        int paise = (int) Math.round((amount - rupees) * 100);
        String words = convertNumberToWords(rupees) + " Rupees";
        if (paise > 0) words += " and " + convertNumberToWords(paise) + " Paise";
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