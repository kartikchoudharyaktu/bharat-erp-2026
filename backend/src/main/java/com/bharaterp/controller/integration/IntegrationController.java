package com.bharaterp.controller.integration;

import com.bharaterp.model.integration.IntegrationConfig;
import com.bharaterp.service.integration.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/integration")
@CrossOrigin(origins = "*")
public class IntegrationController {
    
    @Autowired
    private IntegrationService integrationService;
    
    @PostMapping("/configs")
    public ResponseEntity<IntegrationConfig> createIntegration(@RequestBody IntegrationConfig config) {
        return ResponseEntity.status(HttpStatus.CREATED).body(integrationService.createIntegration(config));
    }
    
    @GetMapping("/configs")
    public ResponseEntity<List<IntegrationConfig>> getAllIntegrations() {
        return ResponseEntity.ok(integrationService.getAllIntegrations());
    }
    
    @GetMapping("/configs/{id}")
    public ResponseEntity<IntegrationConfig> getIntegrationById(@PathVariable Long id) {
        return integrationService.getIntegrationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/configs/name/{name}")
    public ResponseEntity<IntegrationConfig> getIntegrationByName(@PathVariable String name) {
        return integrationService.getIntegrationByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PutMapping("/configs/{id}")
    public ResponseEntity<IntegrationConfig> updateIntegration(@PathVariable Long id, @RequestBody IntegrationConfig config) {
        return ResponseEntity.ok(integrationService.updateIntegration(id, config));
    }
    
    @PutMapping("/configs/{id}/sync")
    public ResponseEntity<IntegrationConfig> syncIntegration(@PathVariable Long id) {
        return ResponseEntity.ok(integrationService.syncIntegration(id));
    }
    
    @DeleteMapping("/configs/{id}")
    public ResponseEntity<Void> deleteIntegration(@PathVariable Long id) {
        integrationService.deleteIntegration(id);
        return ResponseEntity.noContent().build();
    }
}
