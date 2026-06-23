package com.bharaterp.service.mfg_adv;

import com.bharaterp.model.mfg_adv.PlantMaintenance;
import com.bharaterp.model.mfg_adv.ProductLifecycle;
import com.bharaterp.model.mfg_adv.MixedModeManufacturing;
import com.bharaterp.repository.mfg_adv.MFGAdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MFGAdvService {
    
    @Autowired
    private MFGAdvRepository mfgAdvRepository;
    
    // ===== Plant Maintenance =====
    public List<PlantMaintenance> getAllMaintenance() {
        return mfgAdvRepository.findAll();
    }
    
    public PlantMaintenance createMaintenance(PlantMaintenance maintenance) {
        if (maintenance.getMaintenanceNumber() == null) {
            maintenance.setMaintenanceNumber("MTN-" + System.currentTimeMillis());
        }
        return mfgAdvRepository.save(maintenance);
    }
    
    public PlantMaintenance startMaintenance(Long id) {
        PlantMaintenance maintenance = mfgAdvRepository.findById(id).orElseThrow();
        maintenance.setStatus("IN_PROGRESS");
        maintenance.setStartDate(LocalDateTime.now());
        return mfgAdvRepository.save(maintenance);
    }
    
    public PlantMaintenance completeMaintenance(Long id) {
        PlantMaintenance maintenance = mfgAdvRepository.findById(id).orElseThrow();
        maintenance.setStatus("COMPLETED");
        maintenance.setEndDate(LocalDateTime.now());
        return mfgAdvRepository.save(maintenance);
    }
    
    // ===== Product Lifecycle =====
    public List<ProductLifecycle> getAllProductLifecycles() {
        return mfgAdvRepository.findAll();
    }
    
    public ProductLifecycle createProductLifecycle(ProductLifecycle lifecycle) {
        lifecycle.setStage("CONCEPT");
        return mfgAdvRepository.save(lifecycle);
    }
    
    public ProductLifecycle updateStage(Long id, String stage) {
        ProductLifecycle lifecycle = mfgAdvRepository.findById(id).orElseThrow();
        lifecycle.setStage(stage);
        switch (stage) {
            case "CONCEPT": lifecycle.setConceptDate(LocalDateTime.now()); break;
            case "DESIGN": lifecycle.setDesignDate(LocalDateTime.now()); break;
            case "DEVELOPMENT": lifecycle.setDevelopmentDate(LocalDateTime.now()); break;
            case "PRODUCTION": lifecycle.setLaunchDate(LocalDateTime.now()); break;
            case "MATURITY": lifecycle.setMaturityDate(LocalDateTime.now()); break;
            case "DECLINE": lifecycle.setDeclineDate(LocalDateTime.now()); break;
            case "RETIRE": lifecycle.setRetirementDate(LocalDateTime.now()); break;
        }
        return mfgAdvRepository.save(lifecycle);
    }
    
    // ===== Mixed Mode Manufacturing =====
    public List<MixedModeManufacturing> getAllMixedModes() {
        return mfgAdvRepository.findAll();
    }
    
    public MixedModeManufacturing createMixedMode(MixedModeManufacturing mixedMode) {
        return mfgAdvRepository.save(mixedMode);
    }
}
