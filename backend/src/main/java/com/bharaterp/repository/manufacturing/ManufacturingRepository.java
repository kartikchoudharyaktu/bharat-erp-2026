package com.bharaterp.repository.manufacturing;

import com.bharaterp.model.manufacturing.BillOfMaterials;
import com.bharaterp.model.manufacturing.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ManufacturingRepository extends JpaRepository<BillOfMaterials, Long> {
    
    // BOM Queries
    Optional<BillOfMaterials> findByBomCode(String bomCode);
    
    List<BillOfMaterials> findByProductId(Long productId);
    
    List<BillOfMaterials> findByStatus(String status);
    
    List<BillOfMaterials> findByCompanyId(Long companyId);
    
    // Production Order Queries
    Optional<ProductionOrder> findByOrderNumber(String orderNumber);
    
    List<ProductionOrder> findByProductId(Long productId);
    
    List<ProductionOrder> findByStatus(String status);
    
    List<ProductionOrder> findByStatusAndPriority(String status, String priority);
    
    List<ProductionOrder> findByStartDateBetween(LocalDate start, LocalDate end);
    
    List<ProductionOrder> findByCompanyId(Long companyId);
}
