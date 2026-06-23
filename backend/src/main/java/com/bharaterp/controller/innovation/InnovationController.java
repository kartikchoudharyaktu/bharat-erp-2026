package com.bharaterp.controller.innovation;

import com.bharaterp.model.innovation.IndiaStack;
import com.bharaterp.model.innovation.BharatLanguageAI;
import com.bharaterp.model.innovation.SMEAutoPilot;
import com.bharaterp.model.innovation.HyperLocalization;
import com.bharaterp.service.innovation.InnovationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/innovation")
@CrossOrigin(origins = "*")
public class InnovationController {
    
    @Autowired
    private InnovationService innovationService;
    
    // ===== India Stack APIs =====
    @GetMapping("/india-stack")
    public ResponseEntity<List<IndiaStack>> getAllIndiaStackServices() {
        return ResponseEntity.ok(innovationService.getAllIndiaStackServices());
    }
    
    @PostMapping("/india-stack")
    public ResponseEntity<IndiaStack> createIndiaStackService(@RequestBody IndiaStack service) {
        return ResponseEntity.status(HttpStatus.CREATED).body(innovationService.createIndiaStackService(service));
    }
    
    @PutMapping("/india-stack/{id}/sync")
    public ResponseEntity<IndiaStack> syncIndiaStackService(@PathVariable Long id) {
        return ResponseEntity.ok(innovationService.syncIndiaStackService(id));
    }
    
    // ===== Bharat Language AI APIs =====
    @GetMapping("/languages")
    public ResponseEntity<List<BharatLanguageAI>> getAllLanguages() {
        return ResponseEntity.ok(innovationService.getAllLanguages());
    }
    
    @PostMapping("/languages")
    public ResponseEntity<BharatLanguageAI> createLanguage(@RequestBody BharatLanguageAI language) {
        return ResponseEntity.status(HttpStatus.CREATED).body(innovationService.createLanguage(language));
    }
    
    @PutMapping("/languages/{id}/support")
    public ResponseEntity<BharatLanguageAI> updateLanguageSupport(@PathVariable Long id, @RequestParam Boolean isSupported) {
        return ResponseEntity.ok(innovationService.updateLanguageSupport(id, isSupported));
    }
    
    // ===== SME Auto-Pilot APIs =====
    @GetMapping("/auto-pilot")
    public ResponseEntity<List<SMEAutoPilot>> getAllAutoPilots() {
        return ResponseEntity.ok(innovationService.getAllAutoPilots());
    }
    
    @PostMapping("/auto-pilot")
    public ResponseEntity<SMEAutoPilot> createAutoPilot(@RequestBody SMEAutoPilot autoPilot) {
        return ResponseEntity.status(HttpStatus.CREATED).body(innovationService.createAutoPilot(autoPilot));
    }
    
    @PutMapping("/auto-pilot/{id}/level")
    public ResponseEntity<SMEAutoPilot> updateAutoPilotLevel(@PathVariable Long id, @RequestParam String level) {
        return ResponseEntity.ok(innovationService.updateAutoPilotLevel(id, level));
    }
    
    @PutMapping("/auto-pilot/{id}/run")
    public ResponseEntity<SMEAutoPilot> runAutoPilot(@PathVariable Long id) {
        return ResponseEntity.ok(innovationService.runAutoPilot(id));
    }
    
    // ===== Hyper-Localization APIs =====
    @GetMapping("/localization")
    public ResponseEntity<List<HyperLocalization>> getAllLocalizations() {
        return ResponseEntity.ok(innovationService.getAllLocalizations());
    }
    
    @PostMapping("/localization")
    public ResponseEntity<HyperLocalization> createLocalization(@RequestBody HyperLocalization localization) {
        return ResponseEntity.status(HttpStatus.CREATED).body(innovationService.createLocalization(localization));
    }
    
    @PutMapping("/localization/{id}/status")
    public ResponseEntity<HyperLocalization> updateLocalizationStatus(@PathVariable Long id, @RequestParam Boolean isActive) {
        return ResponseEntity.ok(innovationService.updateLocalizationStatus(id, isActive));
    }
}
