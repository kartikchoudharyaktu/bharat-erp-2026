package com.bharaterp.service.innovation;

import com.bharaterp.model.innovation.IndiaStack;
import com.bharaterp.model.innovation.BharatLanguageAI;
import com.bharaterp.model.innovation.SMEAutoPilot;
import com.bharaterp.model.innovation.HyperLocalization;
import com.bharaterp.repository.innovation.InnovationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class InnovationService {
    
    @Autowired
    private InnovationRepository innovationRepository;
    
    // ===== India Stack =====
    public List<IndiaStack> getAllIndiaStackServices() {
        return innovationRepository.findAll();
    }
    
    public IndiaStack createIndiaStackService(IndiaStack service) {
        service.setStatus("ACTIVE");
        return innovationRepository.save(service);
    }
    
    public IndiaStack syncIndiaStackService(Long id) {
        IndiaStack service = innovationRepository.findById(id).orElseThrow();
        service.setLastSyncDate(LocalDateTime.now());
        service.setLastSyncStatus("SUCCESS");
        return innovationRepository.save(service);
    }
    
    // ===== Bharat Language AI =====
    public List<BharatLanguageAI> getAllLanguages() {
        return innovationRepository.findAll();
    }
    
    public BharatLanguageAI createLanguage(BharatLanguageAI language) {
        language.setIsSupported(true);
        return innovationRepository.save(language);
    }
    
    public BharatLanguageAI updateLanguageSupport(Long id, Boolean isSupported) {
        BharatLanguageAI language = innovationRepository.findById(id).orElseThrow();
        language.setIsSupported(isSupported);
        return innovationRepository.save(language);
    }
    
    // ===== SME Auto-Pilot =====
    public List<SMEAutoPilot> getAllAutoPilots() {
        return innovationRepository.findAll();
    }
    
    public SMEAutoPilot createAutoPilot(SMEAutoPilot autoPilot) {
        return innovationRepository.save(autoPilot);
    }
    
    public SMEAutoPilot updateAutoPilotLevel(Long id, String level) {
        SMEAutoPilot autoPilot = innovationRepository.findById(id).orElseThrow();
        autoPilot.setAutoPilotLevel(level);
        return innovationRepository.save(autoPilot);
    }
    
    public SMEAutoPilot runAutoPilot(Long id) {
        SMEAutoPilot autoPilot = innovationRepository.findById(id).orElseThrow();
        autoPilot.setLastRunDate(LocalDateTime.now());
        autoPilot.setLastRunStatus("SUCCESS");
        return innovationRepository.save(autoPilot);
    }
    
    // ===== Hyper-Localization =====
    public List<HyperLocalization> getAllLocalizations() {
        return innovationRepository.findAll();
    }
    
    public HyperLocalization createLocalization(HyperLocalization localization) {
        localization.setIsActive(true);
        return innovationRepository.save(localization);
    }
    
    public HyperLocalization updateLocalizationStatus(Long id, Boolean isActive) {
        HyperLocalization localization = innovationRepository.findById(id).orElseThrow();
        localization.setIsActive(isActive);
        return innovationRepository.save(localization);
    }
}
