package com.bharaterp.service.integration;

import com.bharaterp.model.integration.IntegrationConfig;
import com.bharaterp.repository.integration.IntegrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IntegrationService {
    
    @Autowired
    private IntegrationRepository integrationRepository;
    
    public IntegrationConfig createIntegration(IntegrationConfig config) {
        return integrationRepository.save(config);
    }
    
    public Optional<IntegrationConfig> getIntegrationById(Long id) {
        return integrationRepository.findById(id);
    }
    
    public Optional<IntegrationConfig> getIntegrationByName(String name) {
        return integrationRepository.findByIntegrationName(name);
    }
    
    public IntegrationConfig updateIntegration(Long id, IntegrationConfig configDetails) {
        IntegrationConfig config = integrationRepository.findById(id).orElseThrow();
        config.setBaseUrl(configDetails.getBaseUrl());
        config.setApiKey(configDetails.getApiKey());
        config.setApiSecret(configDetails.getApiSecret());
        config.setAccessToken(configDetails.getAccessToken());
        config.setRefreshToken(configDetails.getRefreshToken());
        config.setStatus(configDetails.getStatus());
        return integrationRepository.save(config);
    }
    
    public IntegrationConfig syncIntegration(Long id) {
        IntegrationConfig config = integrationRepository.findById(id).orElseThrow();
        config.setLastSyncDate(LocalDateTime.now());
        config.setLastSyncStatus("SYNCED");
        return integrationRepository.save(config);
    }
    
    public void deleteIntegration(Long id) {
        integrationRepository.deleteById(id);
    }
}
