package com.bharaterp.service;

import com.bharaterp.model.*;
import com.bharaterp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class AIEngine {

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    // ===== 1. AI CHAT ASSISTANT =====
    public Map<String, Object> chat(String query) {
        Map<String, Object> response = new HashMap<>();
        String q = query.toLowerCase();
        List<String> replies = new ArrayList<>();

        if (q.contains("vendor") || q.contains("supplier")) {
            long count = vendorRepository.count();
            replies.add("You have " + count + " vendors in your system.");
        }

        if (q.contains("product") || q.contains("stock") || q.contains("item")) {
            long count = productRepository.count();
            double totalStock = 0;
            List<Product> products = productRepository.findAll();
            for (Product p : products) totalStock += p.getStock();
            replies.add("You have " + count + " products with total stock of " + totalStock + " units.");
        }

        if (q.contains("sale") || q.contains("invoice")) {
            double total = 0;
            int count = 0;
            List<Invoice> invoices = invoiceRepository.findAll();
            for (Invoice inv : invoices) {
                if (inv.getPosted() == 1) {
                    total += inv.getGrandTotal();
                    count++;
                }
            }
            replies.add("Total sales: Rs." + total + " from " + count + " invoices.");
        }

        if (q.contains("customer")) {
            long count = customerRepository.count();
            replies.add("You have " + count + " customers.");
        }

        if (q.contains("help")) {
            replies.add("I can help you with: vendors, products, sales, customers, stock, invoices. Just ask!");
        }

        if (replies.isEmpty()) {
            replies.add("I didn't understand. Try asking about: vendors, products, sales, customers, stock, or help.");
        }

        response.put("replies", replies);
        response.put("query", query);
        response.put("timestamp", LocalDateTime.now());
        return response;
    }

    // ===== 2. SMART SEARCH =====
    public Map<String, Object> smartSearch(String keyword) {
        Map<String, Object> results = new HashMap<>();
        List<Map<String, Object>> items = new ArrayList<>();

        // Search in products
        List<Product> products = productRepository.findByNameContaining(keyword);
        for (Product p : products) {
            Map<String, Object> item = new HashMap<>();
            item.put("type", "Product");
            item.put("code", p.getCode());
            item.put("name", p.getName());
            item.put("price", p.getSellingPrice());
            item.put("stock", p.getStock());
            items.add(item);
        }

        // Search in customers
        List<Customer> customers = customerRepository.findByNameContaining(keyword);
        for (Customer c : customers) {
            Map<String, Object> item = new HashMap<>();
            item.put("type", "Customer");
            item.put("code", c.getCode());
            item.put("name", c.getName());
            item.put("gstin", c.getGstin());
            item.put("phone", c.getPhone());
            items.add(item);
        }

        // Search in invoices
        List<Invoice> invoices = invoiceRepository.findByCustomerNameContaining(keyword);
        for (Invoice inv : invoices) {
            Map<String, Object> item = new HashMap<>();
            item.put("type", "Invoice");
            item.put("number", inv.getInvoiceNumber());
            item.put("customer", inv.getCustomerName());
            item.put("amount", inv.getGrandTotal());
            item.put("date", inv.getInvoiceDate());
            items.add(item);
        }

        results.put("total", items.size());
        results.put("results", items);
        results.put("keyword", keyword);
        return results;
    }

    // ===== 3. AUTO INVOICE GENERATION =====
    public Map<String, Object> autoGenerateInvoice(String customerName, List<Map<String, Object>> items) {
        Map<String, Object> result = new HashMap<>();
        
        try {
            // Find customer
            Customer customer = null;
            List<Customer> customers = customerRepository.findByNameContaining(customerName);
            if (!customers.isEmpty()) {
                customer = customers.get(0);
            }

            double taxable = 0, totalGst = 0;
            for (Map<String, Object> item : items) {
                double qty = ((Number) item.get("qty")).doubleValue();
                double rate = ((Number) item.get("rate")).doubleValue();
                double gstRate = ((Number) item.getOrDefault("gstRate", 18)).doubleValue();
                double amount = qty * rate;
                double gstAmount = amount * gstRate / 100;
                taxable += amount;
                totalGst += gstAmount;
            }

            String invNo = "AUTO-" + System.currentTimeMillis();
            double grandTotal = taxable + totalGst;

            result.put("invoiceNumber", invNo);
            result.put("customerName", customer != null ? customer.getName() : customerName);
            result.put("customerGstin", customer != null ? customer.getGstin() : "");
            result.put("taxableAmount", taxable);
            result.put("totalGst", totalGst);
            result.put("grandTotal", grandTotal);
            result.put("items", items);
            result.put("status", "AUTO_GENERATED");
            result.put("message", "Invoice generated successfully!");

        } catch (Exception e) {
            result.put("error", e.getMessage());
        }
        return result;
    }

    // ===== 4. ANOMALY DETECTION =====
    public Map<String, Object> detectAnomalies() {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> anomalies = new ArrayList<>();

        // Check for unusually high invoice amounts
        List<Invoice> invoices = invoiceRepository.findAll();
        if (!invoices.isEmpty()) {
            double total = 0;
            int count = 0;
            for (Invoice inv : invoices) {
                if (inv.getPosted() == 1) {
                    total += inv.getGrandTotal();
                    count++;
                }
            }
            double avg = count > 0 ? total / count : 0;
            for (Invoice inv : invoices) {
                if (inv.getPosted() == 1 && inv.getGrandTotal() > avg * 2) {
                    Map<String, Object> anomaly = new HashMap<>();
                    anomaly.put("type", "HIGH_AMOUNT");
                    anomaly.put("invoice", inv.getInvoiceNumber());
                    anomaly.put("amount", inv.getGrandTotal());
                    anomaly.put("avg", avg);
                    anomaly.put("risk", "HIGH");
                    anomalies.add(anomaly);
                }
            }
        }

        // Check for low stock
        List<Product> products = productRepository.findAll();
        for (Product p : products) {
            if (p.getStock() <= p.getMinStock()) {
                Map<String, Object> anomaly = new HashMap<>();
                anomaly.put("type", "LOW_STOCK");
                anomaly.put("product", p.getName());
                anomaly.put("stock", p.getStock());
                anomaly.put("minStock", p.getMinStock());
                anomaly.put("risk", "MEDIUM");
                anomalies.add(anomaly);
            }
        }

        result.put("totalAnomalies", anomalies.size());
        result.put("anomalies", anomalies);
        return result;
    }

    // ===== 5. PREDICTIVE ANALYTICS =====
    public Map<String, Object> predictSales(int days) {
        Map<String, Object> result = new HashMap<>();
        List<Invoice> invoices = invoiceRepository.findAll();

        if (invoices.isEmpty()) {
            result.put("status", "INSUFFICIENT_DATA");
            result.put("message", "No data available for prediction.");
            return result;
        }

        // Calculate average daily sales
        double totalSales = 0;
        int daysWithData = 0;
        for (Invoice inv : invoices) {
            if (inv.getPosted() == 1) {
                totalSales += inv.getGrandTotal();
                daysWithData++;
            }
        }

        double avgDaily = daysWithData > 0 ? totalSales / Math.min(daysWithData, 30) : 0;
        double predictedSales = avgDaily * days;

        result.put("avgDailySales", avgDaily);
        result.put("predictedSales", predictedSales);
        result.put("days", days);
        result.put("confidence", 0.85);
        result.put("status", "SUCCESS");
        return result;
    }

    // ===== 6. QR SCANNER =====
    public Map<String, Object> scanQR(String data) {
        Map<String, Object> result = new HashMap<>();
        result.put("scannedData", data);
        result.put("timestamp", LocalDateTime.now());

        // Check if product code
        if (data.startsWith("PROD-")) {
            List<Product> products = productRepository.findAll();
            for (Product p : products) {
                if (p.getCode().equals(data)) {
                    result.put("type", "PRODUCT");
                    result.put("product", p);
                    result.put("message", "Product found: " + p.getName());
                    return result;
                }
            }
            result.put("type", "UNKNOWN");
            result.put("message", "Product not found: " + data);
            return result;
        }

        // Check if invoice number
        if (data.startsWith("INV-") || data.startsWith("AUTO-")) {
            List<Invoice> invoices = invoiceRepository.findAll();
            for (Invoice inv : invoices) {
                if (inv.getInvoiceNumber().equals(data)) {
                    result.put("type", "INVOICE");
                    result.put("invoice", inv);
                    result.put("message", "Invoice found: " + data);
                    return result;
                }
            }
            result.put("type", "UNKNOWN");
            result.put("message", "Invoice not found: " + data);
            return result;
        }

        result.put("type", "UNKNOWN");
        result.put("message", "Unknown QR code: " + data);
        return result;
    }

    // ===== 7. AI AGENTS =====
    public Map<String, Object> runAgents() {
        Map<String, Object> result = new HashMap<>();
        List<Map<String, Object>> agentResults = new ArrayList<>();

        // Agent 1: Auto Approve Invoices
        Map<String, Object> agent1 = new HashMap<>();
        agent1.put("agent", "Auto Approve Invoices");
        List<Invoice> invoices = invoiceRepository.findAll();
        int approved = 0;
        for (Invoice inv : invoices) {
            if (inv.getPosted() == 0 && inv.getGrandTotal() < 50000) {
                inv.setPosted(1);
                invoiceRepository.save(inv);
                approved++;
            }
        }
        agent1.put("approved", approved);
        agent1.put("status", "SUCCESS");
        agentResults.add(agent1);

        // Agent 2: Low Stock Alert
        Map<String, Object> agent2 = new HashMap<>();
        agent2.put("agent", "Low Stock Alert");
        List<Product> products = productRepository.findAll();
        int lowStockCount = 0;
        for (Product p : products) {
            if (p.getStock() <= p.getMinStock()) {
                lowStockCount++;
            }
        }
        agent2.put("lowStockCount", lowStockCount);
        agent2.put("status", "SUCCESS");
        agentResults.add(agent2);

        result.put("agents", agentResults);
        result.put("timestamp", LocalDateTime.now());
        return result;
    }

    // ===== 8. SMART DATA EXTRACTION =====
    public Map<String, Object> extractData(String text) {
        Map<String, Object> result = new HashMap<>();
        result.put("originalText", text);

        // Extract customer name
        Pattern customerPattern = Pattern.compile("(?:customer|to|bill to):\\s*([^\\n]+)", Pattern.CASE_INSENSITIVE);
        var customerMatcher = customerPattern.matcher(text);
        if (customerMatcher.find()) {
            result.put("customerName", customerMatcher.group(1).trim());
        }

        // Extract GSTIN
        Pattern gstinPattern = Pattern.compile("\\d{2}[A-Z]{5}\\d{4}[A-Z]{1}[1-9A-Z]{1}Z[A-Z0-9]{1}", Pattern.CASE_INSENSITIVE);
        var gstinMatcher = gstinPattern.matcher(text);
        if (gstinMatcher.find()) {
            result.put("gstin", gstinMatcher.group());
        }

        // Extract amounts
        Pattern amountPattern = Pattern.compile("(?:amount|total|grand total):\\s*[₹Rs.]*\\s*([\\d,]+)", Pattern.CASE_INSENSITIVE);
        var amountMatcher = amountPattern.matcher(text);
        if (amountMatcher.find()) {
            result.put("amount", Double.parseDouble(amountMatcher.group(1).replace(",", "")));
        }

        // Extract items
        List<Map<String, Object>> items = new ArrayList<>();
        Pattern itemPattern = Pattern.compile("(\\d+)\\s*[xX*]\\s*([\\d,]+)\\s*[=]?\\s*([\\d,]+)");
        var itemMatcher = itemPattern.matcher(text);
        while (itemMatcher.find()) {
            Map<String, Object> item = new HashMap<>();
            item.put("qty", Double.parseDouble(itemMatcher.group(1)));
            item.put("rate", Double.parseDouble(itemMatcher.group(2).replace(",", "")));
            item.put("amount", Double.parseDouble(itemMatcher.group(3).replace(",", "")));
            items.add(item);
        }
        result.put("items", items);

        result.put("confidence", 0.92);
        result.put("status", "EXTRACTED");
        return result;
    }

    // ===== VENDOR REPOSITORY =====
    @Autowired
    private com.bharaterp.repository.VendorRepository vendorRepository;
}