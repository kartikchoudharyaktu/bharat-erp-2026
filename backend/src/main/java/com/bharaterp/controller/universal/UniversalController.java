package com.bharaterp.controller.universal;

import com.bharaterp.model.universal.UniversalAccess;
import com.bharaterp.model.universal.VoiceCommand;
import com.bharaterp.model.universal.WhatsAppIntegration;
import com.bharaterp.model.universal.ARVRIntegration;
import com.bharaterp.service.universal.UniversalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/universal")
@CrossOrigin(origins = "*")
public class UniversalController {
    
    @Autowired
    private UniversalService universalService;
    
    // ===== Universal Access APIs =====
    @GetMapping("/access")
    public ResponseEntity<List<UniversalAccess>> getAllAccessPoints() {
        return ResponseEntity.ok(universalService.getAllAccessPoints());
    }
    
    @PostMapping("/access")
    public ResponseEntity<UniversalAccess> createAccessPoint(@RequestBody UniversalAccess access) {
        return ResponseEntity.status(HttpStatus.CREATED).body(universalService.createAccessPoint(access));
    }
    
    @PutMapping("/access/{id}/toggle")
    public ResponseEntity<UniversalAccess> toggleAccess(@PathVariable Long id, @RequestParam Boolean isEnabled) {
        return ResponseEntity.ok(universalService.toggleAccess(id, isEnabled));
    }
    
    // ===== Voice Command APIs =====
    @PostMapping("/voice")
    public ResponseEntity<VoiceCommand> processVoiceCommand(@RequestBody VoiceCommand command) {
        return ResponseEntity.status(HttpStatus.CREATED).body(universalService.processVoiceCommand(command));
    }
    
    @GetMapping("/voice/user/{userId}")
    public ResponseEntity<List<VoiceCommand>> getVoiceCommandsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(universalService.getVoiceCommandsByUser(userId));
    }
    
    // ===== WhatsApp APIs =====
    @PostMapping("/whatsapp")
    public ResponseEntity<WhatsAppIntegration> sendWhatsAppMessage(@RequestBody WhatsAppIntegration message) {
        return ResponseEntity.status(HttpStatus.CREATED).body(universalService.sendWhatsAppMessage(message));
    }
    
    @GetMapping("/whatsapp")
    public ResponseEntity<List<WhatsAppIntegration>> getAllWhatsAppMessages() {
        return ResponseEntity.ok(universalService.getAllWhatsAppMessages());
    }
    
    @PutMapping("/whatsapp/{id}/status")
    public ResponseEntity<WhatsAppIntegration> updateWhatsAppStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(universalService.updateWhatsAppStatus(id, status));
    }
    
    // ===== AR/VR APIs =====
    @PostMapping("/arvr")
    public ResponseEntity<ARVRIntegration> createExperience(@RequestBody ARVRIntegration experience) {
        return ResponseEntity.status(HttpStatus.CREATED).body(universalService.createExperience(experience));
    }
    
    @GetMapping("/arvr")
    public ResponseEntity<List<ARVRIntegration>> getAllExperiences() {
        return ResponseEntity.ok(universalService.getAllExperiences());
    }
    
    @PutMapping("/arvr/{id}/deploy")
    public ResponseEntity<ARVRIntegration> deployExperience(@PathVariable Long id) {
        return ResponseEntity.ok(universalService.deployExperience(id));
    }
}
