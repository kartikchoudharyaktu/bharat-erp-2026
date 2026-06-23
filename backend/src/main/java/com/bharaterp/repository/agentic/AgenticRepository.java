package com.bharaterp.repository.agentic;

import com.bharaterp.model.agentic.AgenticAgent;
import com.bharaterp.model.agentic.AgentExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgenticRepository extends JpaRepository<AgenticAgent, Long> {
    
    List<AgenticAgent> findByIsActiveTrue();
    List<AgenticAgent> findByAgentType(String agentType);
    List<AgenticAgent> findByStatus(String status);
    
    List<AgentExecution> findByAgentId(Long agentId);
    List<AgentExecution> findByStatus(String status);
    List<AgentExecution> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
