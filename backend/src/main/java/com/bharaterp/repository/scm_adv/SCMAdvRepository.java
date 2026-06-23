package com.bharaterp.repository.scm_adv;

import com.bharaterp.model.scm_adv.Procurement;
import com.bharaterp.model.scm_adv.WarehouseManagement;
import com.bharaterp.model.scm_adv.Transportation;
import com.bharaterp.model.scm_adv.ReturnLogistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SCMAdvRepository extends JpaRepository<Procurement, Long> {
    
    // Procurement Queries
    List<Procurement> findByVendorId(Long vendorId);
    List<Procurement> findByStatus(String status);
    List<Procurement> findByCategory(String category);
    List<Procurement> findByPriority(String priority);
    
    // Warehouse Management Queries
    List<WarehouseManagement> findByWarehouseType(String warehouseType);
    List<WarehouseManagement> findByStatus(String status);
    List<WarehouseManagement> findByCity(String city);
    
    // Transportation Queries
    List<Transportation> findByTransportMode(String transportMode);
    List<Transportation> findByStatus(String status);
    List<Transportation> findByCarrierName(String carrierName);
    List<Transportation> findByDepartureTimeBetween(LocalDateTime start, LocalDateTime end);
    
    // Return Logistics Queries
    List<ReturnLogistics> findByCustomerId(Long customerId);
    List<ReturnLogistics> findByReturnStatus(String returnStatus);
    List<ReturnLogistics> findByReason(String reason);
}
