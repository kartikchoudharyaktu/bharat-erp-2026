package com.bharaterp.repository.tax;

import com.bharaterp.model.tax.GSTInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface GSTInvoiceRepository extends JpaRepository<GSTInvoice, Long> {
    
    Optional<GSTInvoice> findByInvoiceNumber(String invoiceNumber);
    
    Optional<GSTInvoice> findByIrn(String irn);
    
    List<GSTInvoice> findByCustomerId(Long customerId);
    
    List<GSTInvoice> findByStatus(String status);
    
    List<GSTInvoice> findByInvoiceDateBetween(LocalDateTime start, LocalDateTime end);
    
    List<GSTInvoice> findByInvoiceDateBetweenAndStatus(LocalDateTime start, LocalDateTime end, String status);
}
