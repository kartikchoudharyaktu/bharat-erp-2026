package com.bharaterp.repository.inventory;

import com.bharaterp.model.inventory.ProductInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<ProductInventory, Long> {
    
    Optional<ProductInventory> findByProductCode(String productCode);
    
    List<ProductInventory> findByCategory(String category);
    
    List<ProductInventory> findByStatus(String status);
    
    List<ProductInventory> findByCompanyId(Long companyId);
    
    @Query("SELECT p FROM ProductInventory p WHERE p.currentStock <= p.reorderLevel")
    List<ProductInventory> findLowStockItems();
    
    @Query("SELECT p FROM ProductInventory p WHERE p.currentStock <= p.minStock")
    List<ProductInventory> findCriticalStockItems();
    
    List<ProductInventory> findByProductNameContainingIgnoreCase(String name);
    
    Optional<ProductInventory> findByBarcode(String barcode);
    
    Optional<ProductInventory> findByRfidTag(String rfidTag);
}
