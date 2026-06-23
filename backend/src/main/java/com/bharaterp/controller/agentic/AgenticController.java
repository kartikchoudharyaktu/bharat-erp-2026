package com.bharaterp.controller.agentic;

import com.bharaterp.model.agentic.AgenticAgent;
import com.bharaterp.model.agentic.AgentExecution;
import com.bharaterp.service.agentic.AgenticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/agentic")
@CrossOrigin(origins = "*")
public class AgenticController {
    
    @Autowired
    private AgenticService agenticService;
    
    // ===== Agent Management APIs =====
    @GetMapping("/agents")
    public ResponseEntity<List<AgenticAgent>> getAllAgents() {
        return ResponseEntity.ok(agenticService.getAllAgents());
    }
    
    @GetMapping("/agents/{id}")
    public ResponseEntity<AgenticAgent> getAgentById(@PathVariable Long id) {
        return agenticService.getAgentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/agents")
    public ResponseEntity<AgenticAgent> createAgent(@RequestBody AgenticAgent agent) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agenticService.createAgent(agent));
    }
    
    @PutMapping("/agents/{id}")
    public ResponseEntity<AgenticAgent> updateAgent(@PathVariable Long id, @RequestBody AgenticAgent agent) {
        return ResponseEntity.ok(agenticService.updateAgent(id, agent));
    }
    
    @PutMapping("/agents/{id}/activate")
    public ResponseEntity<AgenticAgent> activateAgent(@PathVariable Long id) {
        return ResponseEntity.ok(agenticService.activateAgent(id));
    }
    
    @PutMapping("/agents/{id}/deactivate")
    public ResponseEntity<AgenticAgent> deactivateAgent(@PathVariable Long id) {
        return ResponseEntity.ok(agenticService.deactivateAgent(id));
    }
    
    // ===== Agent Execution APIs =====
    @PostMapping("/agents/{id}/execute")
    public ResponseEntity<AgentExecution> executeAgent(@PathVariable Long id) {
        return ResponseEntity.ok(agenticService.executeAgent(id));
    }
    
    @GetMapping("/agents/{id}/history")
    public ResponseEntity<List<AgentExecution>> getExecutionHistory(@PathVariable Long id) {
        return ResponseEntity.ok(agenticService.getExecutionHistory(id));
    }
    
    @GetMapping("/executions/recent")
    public ResponseEntity<List<AgentExecution>> getRecentExecutions() {
        return ResponseEntity.ok(agenticService.getRecentExecutions());
    }
}
