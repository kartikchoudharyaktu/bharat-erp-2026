package com.bharaterp.controller.ai;

import com.bharaterp.model.ai.AIModel;
import com.bharaterp.model.ai.AIChat;
import com.bharaterp.model.ai.AIPrediction;
import com.bharaterp.model.ai.AIAnomaly;
import com.bharaterp.service.ai.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AIController {
    
    @Autowired
    private AIService aiService;
    
    // ===== AIModel APIs =====
    @GetMapping("/models")
    public ResponseEntity<List<AIModel>> getAllModels() {
        return ResponseEntity.ok(aiService.getAllModels());
    }
    
    @PostMapping("/models")
    public ResponseEntity<AIModel> createModel(@RequestBody AIModel model) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aiService.createModel(model));
    }
    
    @PutMapping("/models/{id}/train")
    public ResponseEntity<AIModel> trainModel(@PathVariable Long id) {
        return ResponseEntity.ok(aiService.trainModel(id));
    }
    
    @PutMapping("/models/{id}/deploy")
    public ResponseEntity<AIModel> deployModel(@PathVariable Long id) {
        return ResponseEntity.ok(aiService.deployModel(id));
    }
    
    // ===== AIChat APIs =====
    @PostMapping("/chat")
    public ResponseEntity<AIChat> chat(@RequestBody AIChat chat) {
        return ResponseEntity.ok(aiService.processChat(chat));
    }
    
    @GetMapping("/chat/{userId}")
    public ResponseEntity<List<AIChat>> getChatHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(aiService.getChatHistory(userId));
    }
    
    // ===== AIPrediction APIs =====
    @PostMapping("/predict")
    public ResponseEntity<AIPrediction> generatePrediction(@RequestBody AIPrediction prediction) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aiService.generatePrediction(prediction));
    }
    
    @PutMapping("/predict/{id}/validate")
    public ResponseEntity<AIPrediction> validatePrediction(@PathVariable Long id, @RequestParam BigDecimal actualValue) {
        return ResponseEntity.ok(aiService.validatePrediction(id, actualValue));
    }
    
    // ===== AIAnomaly APIs =====
    @PostMapping("/anomaly/detect")
    public ResponseEntity<AIAnomaly> detectAnomaly(@RequestBody AIAnomaly anomaly) {
        return ResponseEntity.status(HttpStatus.CREATED).body(aiService.detectAnomaly(anomaly));
    }
    
    @PutMapping("/anomaly/{id}/resolve")
    public ResponseEntity<AIAnomaly> resolveAnomaly(@PathVariable Long id, @RequestParam String resolutionNotes) {
        return ResponseEntity.ok(aiService.resolveAnomaly(id, resolutionNotes));
    }
    
    // ===== AI Analytics APIs =====
    @GetMapping("/analytics/chat-count")
    public ResponseEntity<Long> getChatCount() {
        return ResponseEntity.ok(aiService.getChatCount());
    }
}
