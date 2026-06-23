package com.bharaterp.controller;

import com.bharaterp.model.*;
import com.bharaterp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/reports")
@CrossOrigin(origins = "*")
public class ReportController {

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private PurchaseOrderRepository poRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private VendorRepository vendorRepository;
    
    @Autowired
    private ProductRepository productRepository;

    // ===== DASHBOARD STATS =====
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Total counts
        stats.put("totalCustomers", customerRepository.count());
        stats.put("totalVendors", vendorRepository.count());
        stats.put("totalProducts", productRepository.count());
        stats.put("totalInvoices", invoiceRepository.count());
        stats.put("totalPOs", poRepository.count());
        
        // Sales summary
        double totalSales = 0;
        double totalGst = 0;
        int invoiceCount = 0;
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice inv : invoices) {
            if (inv.getPosted() == 1) {
                totalSales += inv.getGrandTotal();
                totalGst += inv.getTotalGst();
                invoiceCount++;
            }
        }
        stats.put("totalSales", totalSales);
        stats.put("totalGst", totalGst);
        stats.put("invoiceCount", invoiceCount);
        
        // Purchase summary
        double totalPurchase = 0;
        double purchaseGst = 0;
        int poCount = 0;
        List<PurchaseOrder> pos = poRepository.findAll();
        for (PurchaseOrder po : pos) {
            if (!"CANCELLED".equals(po.getStatus())) {
                totalPurchase += po.getGrandTotal();
                purchaseGst += po.getTotalGst();
                poCount++;
            }
        }
        stats.put("totalPurchase", totalPurchase);
        stats.put("purchaseGst", purchaseGst);
        stats.put("poCount", poCount);
        
        // Monthly sales chart data
        List<Map<String, Object>> monthlySales = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            double monthTotal = 0;
            for (Invoice inv : invoices) {
                if (inv.getPosted() == 1) {
                    try {
                        String[] parts = inv.getInvoiceDate().split("-");
                        int month = Integer.parseInt(parts[1]);
                        if (month == i) monthTotal += inv.getGrandTotal();
                    } catch (Exception e) {}
                }
            }
            Map<String, Object> data = new HashMap<>();
            data.put("month", getMonthName(i));
            data.put("sales", monthTotal);
            monthlySales.add(data);
        }
        stats.put("monthlySales", monthlySales);
        
        // Top products
        List<Map<String, Object>> topProducts = new ArrayList<>();
        Map<String, Double> productSales = new HashMap<>();
        for (Invoice inv : invoices) {
            if (inv.getPosted() == 1) {
                // We need invoice items - skip for now
            }
        }
        stats.put("topProducts", topProducts);
        
        return stats;
    }

    // ===== SALES REPORT =====
    @GetMapping("/sales")
    public Map<String, Object> getSalesReport(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> report = new HashMap<>();
        List<Invoice> invoices = invoiceRepository.findAll();
        
        double totalTaxable = 0, totalGst = 0, totalGrand = 0;
        List<Map<String, Object>> invoiceData = new ArrayList<>();
        
        for (Invoice inv : invoices) {
            if (inv.getPosted() == 1) {
                if (startDate != null && endDate != null) {
                    // Date filter logic
                }
                totalTaxable += inv.getTaxableAmount();
                totalGst += inv.getTotalGst();
                totalGrand += inv.getGrandTotal();
                
                Map<String, Object> data = new HashMap<>();
                data.put("invoiceNumber", inv.getInvoiceNumber());
                data.put("date", inv.getInvoiceDate());
                data.put("customer", inv.getCustomerName());
                data.put("taxable", inv.getTaxableAmount());
                data.put("gst", inv.getTotalGst());
                data.put("grandTotal", inv.getGrandTotal());
                invoiceData.add(data);
            }
        }
        
        report.put("totalInvoices", invoiceData.size());
        report.put("totalTaxable", totalTaxable);
        report.put("totalGst", totalGst);
        report.put("totalGrand", totalGrand);
        report.put("invoices", invoiceData);
        
        return report;
    }

    // ===== PURCHASE REPORT =====
    @GetMapping("/purchase")
    public Map<String, Object> getPurchaseReport(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        Map<String, Object> report = new HashMap<>();
        List<PurchaseOrder> pos = poRepository.findAll();
        
        double totalTaxable = 0, totalGst = 0, totalGrand = 0;
        List<Map<String, Object>> poData = new ArrayList<>();
        
        for (PurchaseOrder po : pos) {
            if (!"CANCELLED".equals(po.getStatus())) {
                totalTaxable += po.getTaxableAmount();
                totalGst += po.getTotalGst();
                totalGrand += po.getGrandTotal();
                
                Map<String, Object> data = new HashMap<>();
                data.put("poNumber", po.getPoNumber());
                data.put("date", po.getPoDate());
                data.put("vendor", po.getVendorName());
                data.put("taxable", po.getTaxableAmount());
                data.put("gst", po.getTotalGst());
                data.put("grandTotal", po.getGrandTotal());
                data.put("status", po.getStatus());
                poData.add(data);
            }
        }
        
        report.put("totalPOs", poData.size());
        report.put("totalTaxable", totalTaxable);
        report.put("totalGst", totalGst);
        report.put("totalGrand", totalGrand);
        report.put("purchaseOrders", poData);
        
        return report;
    }

    // ===== GST REPORT =====
    @GetMapping("/gst")
    public Map<String, Object> getGSTReport() {
        Map<String, Object> report = new HashMap<>();
        List<Invoice> invoices = invoiceRepository.findAll();
        
        double outputTax = 0, inputTax = 0;
        int totalInvoices = 0;
        
        for (Invoice inv : invoices) {
            if (inv.getPosted() == 1) {
                outputTax += inv.getTotalGst();
                totalInvoices++;
            }
        }
        
        // Input tax from purchase orders
        List<PurchaseOrder> pos = poRepository.findAll();
        for (PurchaseOrder po : pos) {
            if (!"CANCELLED".equals(po.getStatus())) {
                inputTax += po.getTotalGst();
            }
        }
        
        report.put("totalInvoices", totalInvoices);
        report.put("outputGST", outputTax);
        report.put("inputGST", inputTax);
        report.put("netGST", outputTax - inputTax);
        report.put("gstr1Summary", generateGSTR1(invoices));
        report.put("gstr3bSummary", generateGSTR3B(invoices, pos));
        
        return report;
    }

    // ===== STOCK REPORT =====
    @GetMapping("/stock")
    public Map<String, Object> getStockReport() {
        Map<String, Object> report = new HashMap<>();
        List<Product> products = productRepository.findAll();
        
        double totalValue = 0;
        List<Map<String, Object>> stockData = new ArrayList<>();
        List<Map<String, Object>> lowStockItems = new ArrayList<>();
        
        for (Product p : products) {
            double value = p.getStock() * p.getSellingPrice();
            totalValue += value;
            
            Map<String, Object> data = new HashMap<>();
            data.put("code", p.getCode());
            data.put("name", p.getName());
            data.put("stock", p.getStock());
            data.put("minStock", p.getMinStock());
            data.put("value", value);
            data.put("status", p.getStock() <= p.getMinStock() ? "LOW" : "NORMAL");
            stockData.add(data);
            
            if (p.getStock() <= p.getMinStock()) {
                Map<String, Object> low = new HashMap<>();
                low.put("code", p.getCode());
                low.put("name", p.getName());
                low.put("stock", p.getStock());
                low.put("minStock", p.getMinStock());
                lowStockItems.add(low);
            }
        }
        
        report.put("totalItems", products.size());
        report.put("totalValue", totalValue);
        report.put("lowStockItems", lowStockItems.size());
        report.put("stockData", stockData);
        report.put("lowStockList", lowStockItems);
        
        return report;
    }

    // ===== CUSTOMER REPORT =====
    @GetMapping("/customers")
    public Map<String, Object> getCustomerReport() {
        Map<String, Object> report = new HashMap<>();
        List<Customer> customers = customerRepository.findAll();
        List<Map<String, Object>> customerData = new ArrayList<>();
        double totalSales = 0;
        
        for (Customer c : customers) {
            double customerTotal = 0;
            List<Invoice> invoices = invoiceRepository.findAll();
            for (Invoice inv : invoices) {
                if (inv.getPosted() == 1 && inv.getCustomerName().equals(c.getName())) {
                    customerTotal += inv.getGrandTotal();
                }
            }
            totalSales += customerTotal;
            
            Map<String, Object> data = new HashMap<>();
            data.put("code", c.getCode());
            data.put("name", c.getName());
            data.put("gstin", c.getGstin());
            data.put("phone", c.getPhone());
            data.put("city", c.getCity());
            data.put("totalSales", customerTotal);
            customerData.add(data);
        }
        
        report.put("totalCustomers", customers.size());
        report.put("totalSales", totalSales);
        report.put("customers", customerData);
        
        return report;
    }

    // ===== VENDOR REPORT =====
    @GetMapping("/vendors")
    public Map<String, Object> getVendorReport() {
        Map<String, Object> report = new HashMap<>();
        List<Vendor> vendors = vendorRepository.findAll();
        List<Map<String, Object>> vendorData = new ArrayList<>();
        double totalPurchase = 0;
        
        for (Vendor v : vendors) {
            double vendorTotal = 0;
            List<PurchaseOrder> pos = poRepository.findAll();
            for (PurchaseOrder po : pos) {
                if (po.getVendorName().equals(v.getName())) {
                    vendorTotal += po.getGrandTotal();
                }
            }
            totalPurchase += vendorTotal;
            
            Map<String, Object> data = new HashMap<>();
            data.put("code", v.getCode());
            data.put("name", v.getName());
            data.put("gstin", v.getGstin());
            data.put("phone", v.getPhone());
            data.put("city", v.getCity());
            data.put("totalPurchase", vendorTotal);
            vendorData.add(data);
        }
        
        report.put("totalVendors", vendors.size());
        report.put("totalPurchase", totalPurchase);
        report.put("vendors", vendorData);
        
        return report;
    }

    // ===== TRIAL BALANCE =====
    @GetMapping("/trial-balance")
    public Map<String, Object> getTrialBalance() {
        Map<String, Object> report = new HashMap<>();
        
        List<Map<String, Object>> accounts = new ArrayList<>();
        Map<String, Object> cash = new HashMap<>();
        cash.put("code", "1000");
        cash.put("name", "Cash");
        cash.put("type", "ASSET");
        cash.put("debit", 50000.0);
        cash.put("credit", 0.0);
        cash.put("balance", 50000.0);
        accounts.add(cash);
        
        Map<String, Object> bank = new HashMap<>();
        bank.put("code", "1001");
        bank.put("name", "Bank Account");
        bank.put("type", "ASSET");
        bank.put("debit", 200000.0);
        bank.put("credit", 0.0);
        bank.put("balance", 200000.0);
        accounts.add(bank);
        
        double totalDebit = 250000.0;
        double totalCredit = 0.0;
        
        report.put("accounts", accounts);
        report.put("totalDebit", totalDebit);
        report.put("totalCredit", totalCredit);
        
        return report;
    }

    // ===== PROFIT & LOSS =====
    @GetMapping("/profit-loss")
    public Map<String, Object> getProfitLoss() {
        Map<String, Object> report = new HashMap<>();
        
        double totalRevenue = 0;
        double totalExpenses = 0;
        
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice inv : invoices) {
            if (inv.getPosted() == 1) {
                totalRevenue += inv.getGrandTotal();
            }
        }
        
        // Simulate expenses (30% of revenue)
        totalExpenses = totalRevenue * 0.3;
        
        double grossProfit = totalRevenue - totalExpenses;
        double netProfit = grossProfit * 0.8; // After tax
        
        report.put("totalRevenue", totalRevenue);
        report.put("totalExpenses", totalExpenses);
        report.put("grossProfit", grossProfit);
        report.put("netProfit", netProfit);
        report.put("profitMargin", totalRevenue > 0 ? (netProfit / totalRevenue) * 100 : 0);
        
        return report;
    }

    // ===== HELPER METHODS =====
    private String getMonthName(int month) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return months[month - 1];
    }

    private Map<String, Object> generateGSTR1(List<Invoice> invoices) {
        Map<String, Object> gstr1 = new HashMap<>();
        double totalTaxable = 0, totalGst = 0, totalGrand = 0;
        int totalCount = 0;
        
        for (Invoice inv : invoices) {
            if (inv.getPosted() == 1) {
                totalTaxable += inv.getTaxableAmount();
                totalGst += inv.getTotalGst();
                totalGrand += inv.getGrandTotal();
                totalCount++;
            }
        }
        
        gstr1.put("totalInvoices", totalCount);
        gstr1.put("totalTaxable", totalTaxable);
        gstr1.put("totalGST", totalGst);
        gstr1.put("totalGrand", totalGrand);
        gstr1.put("cgst", totalGst / 2);
        gstr1.put("sgst", totalGst / 2);
        
        return gstr1;
    }

    private Map<String, Object> generateGSTR3B(List<Invoice> invoices, List<PurchaseOrder> pos) {
        Map<String, Object> gstr3b = new HashMap<>();
        double outputTax = 0, inputTax = 0;
        
        for (Invoice inv : invoices) {
            if (inv.getPosted() == 1) {
                outputTax += inv.getTotalGst();
            }
        }
        
        for (PurchaseOrder po : pos) {
            inputTax += po.getTotalGst();
        }
        
        gstr3b.put("outputGST", outputTax);
        gstr3b.put("inputGST", inputTax);
        gstr3b.put("netPayable", outputTax - inputTax);
        
        return gstr3b;
    }
}