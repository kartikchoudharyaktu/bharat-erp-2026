package com.bharaterp.service.ai;

import com.bharaterp.model.ai.AIModel;
import com.bharaterp.model.ai.AIChat;
import com.bharaterp.model.ai.AIPrediction;
import com.bharaterp.model.ai.AIAnomaly;
import com.bharaterp.repository.ai.AIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.math.BigDecimal;

@Service
public class AIService {
    
    @Autowired
    private AIRepository aiRepository;
    
    // ===== AIModel Operations =====
    public List<AIModel> getAllModels() {
        return aiRepository.findAll();
    }
    
    public Optional<AIModel> getModelById(Long id) {
        return aiRepository.findById(id);
    }
    
    public AIModel createModel(AIModel model) {
        return aiRepository.save(model);
    }
    
    public AIModel trainModel(Long id) {
        AIModel model = aiRepository.findById(id).orElseThrow();
        model.setStatus("TRAINING");
        model.setTrainingDate(LocalDateTime.now());
        // Simulate training
        model.setAccuracy(new Random().nextDouble() * 0.3 + 0.7);
        model.setPrecision(new Random().nextDouble() * 0.3 + 0.7);
        model.setRecall(new Random().nextDouble() * 0.3 + 0.7);
        model.setF1Score(new Random().nextDouble() * 0.3 + 0.7);
        model.setStatus("READY");
        return aiRepository.save(model);
    }
    
    public AIModel deployModel(Long id) {
        AIModel model = aiRepository.findById(id).orElseThrow();
        model.setStatus("DEPLOYED");
        model.setDeploymentDate(LocalDateTime.now());
        return aiRepository.save(model);
    }
    
    // ===== AIChat Operations =====
    public List<AIChat> getChatHistory(Long userId) {
        return aiRepository.findByUserId(userId);
    }
    
    public AIChat processChat(AIChat chat) {
        // Simulate AI response
        String response = generateAIResponse(chat.getUserMessage());
        chat.setAiResponse(response);
        chat.setConfidence(new Random().nextDouble() * 0.2 + 0.8);
        return aiRepository.save(chat);
    }
    
    private String generateAIResponse(String message) {
        // Simple AI response simulation
        if (message.toLowerCase().contains("sales")) {
            return "📊 Sales summary: Total sales this month: ₹1,50,000. 25% increase from last month.";
        } else if (message.toLowerCase().contains("stock") || message.toLowerCase().contains("inventory")) {
            return "📦 Stock status: 15 products below reorder level. Critical items: Product A (5 units), Product B (3 units).";
        } else if (message.toLowerCase().contains("employee") || message.toLowerCase().contains("hr")) {
            return "👥 HR summary: 42 active employees. 3 leave requests pending approval. 2 new hires this month.";
        } else if (message.toLowerCase().contains("invoice")) {
            return "📄 Invoice summary: 25 invoices generated this month. Total amount: ₹4,50,000. 8 invoices overdue.";
        } else if (message.toLowerCase().contains("help")) {
            return "💡 I can help you with:\n- 📊 Sales reports\n- 📦 Inventory status\n- 👥 HR information\n- 📄 Invoice data\n- 📈 Financial analytics";
        } else {
            return "🔍 I've processed your request. Please specify a module like sales, stock, employee, invoice, or financial for more detailed information.";
        }
    }
    
    // ===== AIPrediction Operations =====
    public List<AIPrediction> getPredictions() {
        return aiRepository.findAll();
    }
    
    public AIPrediction generatePrediction(AIPrediction prediction) {
        // Simulate prediction
        Random rand = new Random();
        double baseValue = 1000 + rand.nextDouble() * 9000;
        prediction.setPredictedValue(BigDecimal.valueOf(baseValue));
        prediction.setConfidence(0.7 + rand.nextDouble() * 0.25);
        prediction.setStatus("PENDING");
        return aiRepository.save(prediction);
    }
    
    public AIPrediction validatePrediction(Long id, BigDecimal actualValue) {
        AIPrediction prediction = aiRepository.findById(id).orElseThrow();
        prediction.setActualValue(actualValue);
        prediction.setStatus("VALIDATED");
        prediction.setValidationDate(LocalDateTime.now());
        return aiRepository.save(prediction);
    }
    
    // ===== AIAnomaly Operations =====
    public List<AIAnomaly> getAnomalies() {
        return aiRepository.findAll();
    }
    
    public AIAnomaly detectAnomaly(AIAnomaly anomaly) {
        // Simulate anomaly detection
        anomaly.setStatus("NEW");
        return aiRepository.save(anomaly);
    }
    
    public AIAnomaly resolveAnomaly(Long id, String resolutionNotes) {
        AIAnomaly anomaly = aiRepository.findById(id).orElseThrow();
        anomaly.setStatus("RESOLVED");
        anomaly.setResolutionNotes(resolutionNotes);
        return aiRepository.save(anomaly);
    }
    
    // ===== AI Analytics =====
    public Long getChatCount() {
        return aiRepository.count();
    }
    
    public Long getAnomalyCount() {
        return aiRepository.count();
    }
}
