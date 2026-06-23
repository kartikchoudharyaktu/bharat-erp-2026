package com.bharaterp.repository.innovation;

import com.bharaterp.model.innovation.IndiaStack;
import com.bharaterp.model.innovation.BharatLanguageAI;
import com.bharaterp.model.innovation.SMEAutoPilot;
import com.bharaterp.model.innovation.HyperLocalization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InnovationRepository extends JpaRepository<IndiaStack, Long> {
    
    // India Stack Queries
    List<IndiaStack> findByServiceName(String serviceName);
    List<IndiaStack> findByStatus(String status);
    List<IndiaStack> findByIsEnabledTrue();
    
    // Bharat Language AI Queries
    List<BharatLanguageAI> findByLanguageCode(String languageCode);
    List<BharatLanguageAI> findByIsSupportedTrue();
    List<BharatLanguageAI> findByVoiceProvider(String voiceProvider);
    
    // SME Auto-Pilot Queries
    List<SMEAutoPilot> findByUserId(Long userId);
    List<SMEAutoPilot> findByBusinessType(String businessType);
    List<SMEAutoPilot> findByAutoPilotLevel(String autoPilotLevel);
    
    // Hyper-Localization Queries
    List<HyperLocalization> findByState(String state);
    List<HyperLocalization> findByRegion(String region);
    List<HyperLocalization> findByIsActiveTrue();
}
