package com.bharaterp.repository.sales;

import com.bharaterp.model.sales.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SalesRepository extends JpaRepository<SalesOrder, Long> {
    
    List<SalesOrder> findByCustomerId(Long customerId);
    
    List<SalesOrder> findByOrderStatus(String status);
    
    List<SalesOrder> findByPaymentStatus(String paymentStatus);
    
    List<SalesOrder> findByOrderDateBetween(LocalDateTime start, LocalDateTime end);
    
    List<SalesOrder> findByCompanyId(Long companyId);
}
