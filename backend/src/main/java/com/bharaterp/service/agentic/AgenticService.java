package com.bharaterp.service.agentic;

import com.bharaterp.model.agentic.AgenticAgent;
import com.bharaterp.model.agentic.AgentExecution;
import com.bharaterp.repository.agentic.AgenticRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AgenticService {
    
    @Autowired
    private AgenticRepository agenticRepository;
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    // ===== Agent Management =====
    public List<AgenticAgent> getAllAgents() {
        return agenticRepository.findAll();
    }
    
    public Optional<AgenticAgent> getAgentById(Long id) {
        return agenticRepository.findById(id);
    }
    
    public AgenticAgent createAgent(AgenticAgent agent) {
        agent.setStatus("IDLE");
        return agenticRepository.save(agent);
    }
    
    public AgenticAgent updateAgent(Long id, AgenticAgent agentDetails) {
        AgenticAgent agent = agenticRepository.findById(id).orElseThrow();
        agent.setAgentName(agentDetails.getAgentName());
        agent.setDescription(agentDetails.getDescription());
        agent.setTriggerCondition(agentDetails.getTriggerCondition());
        agent.setActionConfig(agentDetails.getActionConfig());
        agent.setIsActive(agentDetails.getIsActive());
        agent.setExecutionSchedule(agentDetails.getExecutionSchedule());
        agent.setPriority(agentDetails.getPriority());
        return agenticRepository.save(agent);
    }
    
    public AgenticAgent activateAgent(Long id) {
        AgenticAgent agent = agenticRepository.findById(id).orElseThrow();
        agent.setIsActive(true);
        return agenticRepository.save(agent);
    }
    
    public AgenticAgent deactivateAgent(Long id) {
        AgenticAgent agent = agenticRepository.findById(id).orElseThrow();
        agent.setIsActive(false);
        return agenticRepository.save(agent);
    }
    
    // ===== Agent Execution =====
    public AgentExecution executeAgent(Long agentId) {
        AgenticAgent agent = agenticRepository.findById(agentId).orElseThrow();
        
        AgentExecution execution = new AgentExecution();
        execution.setAgentId(agentId);
        execution.setAgentName(agent.getAgentName());
        execution.setAgentType(agent.getAgentType());
        execution.setStatus("PROCESSING");
        execution.setStartTime(LocalDateTime.now());
        
        // Simulate execution
        try {
            String result = processAgentAction(agent);
            execution.setStatus("COMPLETED");
            execution.setResultSummary(result);
            execution.setItemsProcessed(10);
            execution.setItemsApproved(8);
            execution.setItemsRejected(2);
            
            agent.setSuccessCount(agent.getSuccessCount() + 1);
            agent.setStatus("COMPLETED");
            agent.setLastExecutionAt(LocalDateTime.now());
            agent.setLastExecutionResult("SUCCESS");
            
        } catch (Exception e) {
            execution.setStatus("FAILED");
            execution.setErrorMessage(e.getMessage());
            agent.setFailureCount(agent.getFailureCount() + 1);
            agent.setStatus("FAILED");
            agent.setLastExecutionResult("FAILED: " + e.getMessage());
        }
        
        execution.setEndTime(LocalDateTime.now());
        agenticRepository.save(agent);
        return agenticRepository.save(execution);
    }
    
    private String processAgentAction(AgenticAgent agent) {
        String agentType = agent.getAgentType();
        switch (agentType) {
            case "INVOICE_APPROVER":
                return "✅ Processed 10 invoices: 8 approved, 2 rejected";
            case "PO_GENERATOR":
                return "✅ Generated 5 Purchase Orders for low stock items";
            case "RECONCILIATOR":
                return "✅ Matched 45 transactions: 40 exact, 5 suggested";
            case "EMAIL_PROCESSOR":
                return "✅ Processed 12 emails: 8 converted to orders, 4 pending";
            case "SCHEDULER":
                return "✅ Scheduled 15 production orders for next week";
            default:
                return "✅ Agent executed successfully";
        }
    }
    
    // ===== Agent Execution History =====
    public List<AgentExecution> getExecutionHistory(Long agentId) {
        return agenticRepository.findByAgentId(agentId);
    }
    
    public List<AgentExecution> getRecentExecutions() {
        return agenticRepository.findByCreatedAtBetween(
            LocalDateTime.now().minusDays(7),
            LocalDateTime.now()
        );
    }
}
