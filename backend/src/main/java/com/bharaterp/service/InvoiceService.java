package com.bharaterp.service;

import com.bharaterp.model.Invoice;
import com.bharaterp.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    
    @Autowired
    private InvoiceRepository invoiceRepository;
    
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
    
    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }
    
    public Invoice createInvoice(Invoice invoice) {
        // Generate invoice number
        invoice.setInvoiceNumber("INV-" + System.currentTimeMillis());
        return invoiceRepository.save(invoice);
    }
    
    public Invoice updateInvoice(Long id, Invoice invoiceDetails) {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow();
        invoice.setCustomerId(invoiceDetails.getCustomerId());
        invoice.setInvoiceDate(invoiceDetails.getInvoiceDate());
        invoice.setDueDate(invoiceDetails.getDueDate());
        invoice.setTotal(invoiceDetails.getTotal());
        invoice.setStatus(invoiceDetails.getStatus());
        return invoiceRepository.save(invoice);
    }
    
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
    
    public List<Invoice> getInvoicesByCustomer(Long customerId) {
        return invoiceRepository.findAll();
    }
}
