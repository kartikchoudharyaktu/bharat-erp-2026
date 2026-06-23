package com.bharaterp.repository.integration;

import com.bharaterp.model.integration.IntegrationConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IntegrationRepository extends JpaRepository<IntegrationConfig, Long> {
    
    Optional<IntegrationConfig> findByIntegrationName(String integrationName);
    List<IntegrationConfig> findByIntegrationType(String integrationType);
    List<IntegrationConfig> findByStatus(String status);
}
