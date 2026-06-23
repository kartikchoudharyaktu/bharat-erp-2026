package com.bharaterp.repository.ai;

import com.bharaterp.model.ai.AIModel;
import com.bharaterp.model.ai.AIChat;
import com.bharaterp.model.ai.AIPrediction;
import com.bharaterp.model.ai.AIAnomaly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AIRepository extends JpaRepository<AIModel, Long> {
    
    // AIModel Queries
    List<AIModel> findByModelType(String modelType);
    List<AIModel> findByStatus(String status);
    
    // AIChat Queries
    List<AIChat> findByUserId(Long userId);
    List<AIChat> findBySessionId(String sessionId);
    List<AIChat> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    List<AIChat> findByModule(String module);
    
    // AIPrediction Queries
    List<AIPrediction> findByPredictionType(String predictionType);
    List<AIPrediction> findByModule(String module);
    List<AIPrediction> findByStatus(String status);
    List<AIPrediction> findByPredictionFor(String predictionFor);
    
    // AIAnomaly Queries
    List<AIAnomaly> findByAnomalyType(String anomalyType);
    List<AIAnomaly> findBySeverity(String severity);
    List<AIAnomaly> findByStatus(String status);
    List<AIAnomaly> findByModule(String module);
}
