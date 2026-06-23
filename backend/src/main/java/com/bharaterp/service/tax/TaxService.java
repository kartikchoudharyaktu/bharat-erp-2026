package com.bharaterp.service.tax;

import com.bharaterp.model.tax.GSTInvoice;
import com.bharaterp.model.tax.TaxCompliance;
import com.bharaterp.repository.tax.GSTInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaxService {
    
    @Autowired
    private GSTInvoiceRepository gstInvoiceRepository;
    
    // GST Invoice Operations
    public List<GSTInvoice> getAllGSTInvoices() {
        return gstInvoiceRepository.findAll();
    }
    
    public Optional<GSTInvoice> getGSTInvoiceById(Long id) {
        return gstInvoiceRepository.findById(id);
    }
    
    public Optional<GSTInvoice> getGSTInvoiceByNumber(String invoiceNumber) {
        return gstInvoiceRepository.findByInvoiceNumber(invoiceNumber);
    }
    
    public GSTInvoice createGSTInvoice(GSTInvoice invoice) {
        // Auto-generate invoice number
        if (invoice.getInvoiceNumber() == null || invoice.getInvoiceNumber().isEmpty()) {
            invoice.setInvoiceNumber("INV-" + System.currentTimeMillis());
        }
        invoice.setStatus("DRAFT");
        return gstInvoiceRepository.save(invoice);
    }
    
    public GSTInvoice updateGSTInvoice(Long id, GSTInvoice invoiceDetails) {
        GSTInvoice invoice = gstInvoiceRepository.findById(id).orElseThrow();
        invoice.setCustomerId(invoiceDetails.getCustomerId());
        invoice.setCustomerName(invoiceDetails.getCustomerName());
        invoice.setCustomerGstin(invoiceDetails.getCustomerGstin());
        invoice.setInvoiceDate(invoiceDetails.getInvoiceDate());
        invoice.setDueDate(invoiceDetails.getDueDate());
        invoice.setGrandTotal(invoiceDetails.getGrandTotal());
        invoice.setStatus(invoiceDetails.getStatus());
        return gstInvoiceRepository.save(invoice);
    }
    
    public GSTInvoice submitGSTInvoice(Long id) {
        GSTInvoice invoice = gstInvoiceRepository.findById(id).orElseThrow();
        invoice.setStatus("FINAL");
        return gstInvoiceRepository.save(invoice);
    }
    
    public GSTInvoice cancelGSTInvoice(Long id) {
        GSTInvoice invoice = gstInvoiceRepository.findById(id).orElseThrow();
        invoice.setStatus("CANCELLED");
        return gstInvoiceRepository.save(invoice);
    }
    
    public void deleteGSTInvoice(Long id) {
        gstInvoiceRepository.deleteById(id);
    }
    
    // Tax Calculation Methods
    public BigDecimal calculateCGST(BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate).divide(BigDecimal.valueOf(100));
    }
    
    public BigDecimal calculateSGST(BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate).divide(BigDecimal.valueOf(100));
    }
    
    public BigDecimal calculateIGST(BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate).divide(BigDecimal.valueOf(100));
    }
    
    public BigDecimal calculateTotalGST(BigDecimal cgst, BigDecimal sgst, BigDecimal igst) {
        return cgst.add(sgst).add(igst);
    }
    
    public BigDecimal calculateGrandTotal(BigDecimal taxableAmount, BigDecimal totalGST) {
        return taxableAmount.add(totalGST);
    }
    
    // GST Returns
    public List<GSTInvoice> getInvoicesByDateRange(LocalDateTime start, LocalDateTime end) {
        return gstInvoiceRepository.findByInvoiceDateBetween(start, end);
    }
    
    public BigDecimal getTotalSales(LocalDateTime start, LocalDateTime end) {
        return gstInvoiceRepository.findByInvoiceDateBetweenAndStatus(start, end, "FINAL")
                .stream()
                .map(GSTInvoice::getGrandTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    
    public BigDecimal getTotalGST(LocalDateTime start, LocalDateTime end) {
        return gstInvoiceRepository.findByInvoiceDateBetweenAndStatus(start, end, "FINAL")
                .stream()
                .map(GSTInvoice::getTotalGST)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
