package com.bharaterp.repository.universal;

import com.bharaterp.model.universal.UniversalAccess;
import com.bharaterp.model.universal.VoiceCommand;
import com.bharaterp.model.universal.WhatsAppIntegration;
import com.bharaterp.model.universal.ARVRIntegration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UniversalRepository extends JpaRepository<UniversalAccess, Long> {
    
    // Universal Access Queries
    List<UniversalAccess> findByAccessType(String accessType);
    List<UniversalAccess> findByIsEnabledTrue();
    List<UniversalAccess> findByStatus(String status);
    
    // Voice Command Queries
    List<VoiceCommand> findByUserId(Long userId);
    List<VoiceCommand> findByLanguageCode(String languageCode);
    List<VoiceCommand> findByCommandType(String commandType);
    List<VoiceCommand> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    
    // WhatsApp Integration Queries
    List<WhatsAppIntegration> findByPhoneNumber(String phoneNumber);
    List<WhatsAppIntegration> findByStatus(String status);
    List<WhatsAppIntegration> findByDirection(String direction);
    
    // AR/VR Integration Queries
    List<ARVRIntegration> findByExperienceType(String experienceType);
    List<ARVRIntegration> findByPlatform(String platform);
    List<ARVRIntegration> findByStatus(String status);
}
