package com.bharaterp.controller.mfg_adv;

import com.bharaterp.model.mfg_adv.PlantMaintenance;
import com.bharaterp.model.mfg_adv.ProductLifecycle;
import com.bharaterp.model.mfg_adv.MixedModeManufacturing;
import com.bharaterp.service.mfg_adv.MFGAdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mfg-adv")
@CrossOrigin(origins = "*")
public class MFGAdvController {
    
    @Autowired
    private MFGAdvService mfgAdvService;
    
    // ===== Plant Maintenance APIs =====
    @GetMapping("/maintenance")
    public ResponseEntity<List<PlantMaintenance>> getAllMaintenance() {
        return ResponseEntity.ok(mfgAdvService.getAllMaintenance());
    }
    
    @PostMapping("/maintenance")
    public ResponseEntity<PlantMaintenance> createMaintenance(@RequestBody PlantMaintenance maintenance) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mfgAdvService.createMaintenance(maintenance));
    }
    
    @PutMapping("/maintenance/{id}/start")
    public ResponseEntity<PlantMaintenance> startMaintenance(@PathVariable Long id) {
        return ResponseEntity.ok(mfgAdvService.startMaintenance(id));
    }
    
    @PutMapping("/maintenance/{id}/complete")
    public ResponseEntity<PlantMaintenance> completeMaintenance(@PathVariable Long id) {
        return ResponseEntity.ok(mfgAdvService.completeMaintenance(id));
    }
    
    // ===== Product Lifecycle APIs =====
    @GetMapping("/plm")
    public ResponseEntity<List<ProductLifecycle>> getAllProductLifecycles() {
        return ResponseEntity.ok(mfgAdvService.getAllProductLifecycles());
    }
    
    @PostMapping("/plm")
    public ResponseEntity<ProductLifecycle> createProductLifecycle(@RequestBody ProductLifecycle lifecycle) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mfgAdvService.createProductLifecycle(lifecycle));
    }
    
    @PutMapping("/plm/{id}/stage")
    public ResponseEntity<ProductLifecycle> updateStage(@PathVariable Long id, @RequestParam String stage) {
        return ResponseEntity.ok(mfgAdvService.updateStage(id, stage));
    }
    
    // ===== Mixed Mode Manufacturing APIs =====
    @GetMapping("/mixed-mode")
    public ResponseEntity<List<MixedModeManufacturing>> getAllMixedModes() {
        return ResponseEntity.ok(mfgAdvService.getAllMixedModes());
    }
    
    @PostMapping("/mixed-mode")
    public ResponseEntity<MixedModeManufacturing> createMixedMode(@RequestBody MixedModeManufacturing mixedMode) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mfgAdvService.createMixedMode(mixedMode));
    }
}
