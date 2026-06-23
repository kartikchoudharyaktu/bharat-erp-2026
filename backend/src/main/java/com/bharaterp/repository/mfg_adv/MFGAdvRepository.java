package com.bharaterp.repository.mfg_adv;

import com.bharaterp.model.mfg_adv.PlantMaintenance;
import com.bharaterp.model.mfg_adv.ProductLifecycle;
import com.bharaterp.model.mfg_adv.MixedModeManufacturing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MFGAdvRepository extends JpaRepository<PlantMaintenance, Long> {
    
    // Plant Maintenance Queries
    List<PlantMaintenance> findByEquipmentName(String equipmentName);
    List<PlantMaintenance> findByMaintenanceType(String maintenanceType);
    List<PlantMaintenance> findByStatus(String status);
    List<PlantMaintenance> findByPriority(String priority);
    
    // Product Lifecycle Queries
    List<ProductLifecycle> findByStage(String stage);
    List<ProductLifecycle> findByCategory(String category);
    List<ProductLifecycle> findByStatus(String status);
    
    // Mixed Mode Manufacturing Queries
    List<MixedModeManufacturing> findByMode(String mode);
    List<MixedModeManufacturing> findByStatus(String status);
}
