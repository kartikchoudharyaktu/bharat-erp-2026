package com.bharaterp.service.enhancement;

import com.bharaterp.model.enhancement.EmailToOrder;
import com.bharaterp.model.enhancement.BankReconciliationAI;
import com.bharaterp.model.enhancement.DigitalTwin;
import com.bharaterp.repository.enhancement.EnhancementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.Random;

@Service
public class EnhancementService {
    
    @Autowired
    private EnhancementRepository enhancementRepository;
    
    // ===== Email to Order =====
    public List<EmailToOrder> getAllEmails() {
        return enhancementRepository.findAll();
    }
    
    public Optional<EmailToOrder> getEmailById(Long id) {
        return enhancementRepository.findById(id);
    }
    
    public EmailToOrder processEmail(EmailToOrder email) {
        // Simulate AI processing
        String extractedText = "Customer: " + email.getSenderName() + 
                               ", Amount: ₹" + (5000 + new Random().nextDouble() * 10000);
        email.setExtractedData(extractedText);
        email.setConfidence(String.valueOf(0.75 + new Random().nextDouble() * 0.2));
        email.setStatus("PROCESSED");
        email.setProcessedAt(LocalDateTime.now());
        email.setProcessedBy("AI-Email-Agent");
        email.setOrderNumber("PO-" + System.currentTimeMillis());
        return enhancementRepository.save(email);
    }
    
    // ===== AI Reconciliation =====
    public List<BankReconciliationAI> getAllReconciliations() {
        return enhancementRepository.findAll();
    }
    
    public BankReconciliationAI performReconciliation(BankReconciliationAI recon) {
        // Simulate AI matching
        Random rand = new Random();
        double score = 0.7 + rand.nextDouble() * 0.3;
        recon.setMatchScore(score);
        recon.setConfidence(String.valueOf(score));
        if (score > 0.9) {
            recon.setMatchType("EXACT");
            recon.setStatus("MATCHED");
        } else if (score > 0.7) {
            recon.setMatchType("NEAR_EXACT");
            recon.setStatus("PENDING");
            recon.setSuggestions("{\"suggestion\": \"Check transaction date\"}");
        } else {
            recon.setMatchType("MANUAL");
            recon.setStatus("UNMATCHED");
        }
        return enhancementRepository.save(recon);
    }
    
    public BankReconciliationAI resolveReconciliation(Long id, String notes) {
        BankReconciliationAI recon = enhancementRepository.findById(id).orElseThrow();
        recon.setStatus("RESOLVED");
        recon.setResolvedAt(LocalDateTime.now());
        recon.setResolutionNotes(notes);
        return enhancementRepository.save(recon);
    }
    
    // ===== Digital Twin =====
    public List<DigitalTwin> getAllTwins() {
        return enhancementRepository.findAll();
    }
    
    public DigitalTwin createTwin(DigitalTwin twin) {
        twin.setAccuracy(0.85 + new Random().nextDouble() * 0.1);
        twin.setConfidence(0.8 + new Random().nextDouble() * 0.15);
        return enhancementRepository.save(twin);
    }
    
    public DigitalTwin simulateTwin(Long id) {
        DigitalTwin twin = enhancementRepository.findById(id).orElseThrow();
        twin.setStatus("SIMULATING");
        String simulationResult = "{\"prediction\": \"Production efficiency: " + 
                                  (85 + new Random().nextInt(10)) + "%\"}";
        twin.setSimulationData(simulationResult);
        twin.setLastSimulationResult("Success");
        twin.setLastSimulationAt(LocalDateTime.now());
        twin.setStatus("ACTIVE");
        return enhancementRepository.save(twin);
    }
}
