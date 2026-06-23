package com.bharaterp.controller.quantum;

import com.bharaterp.model.quantum.QuantumEncryption;
import com.bharaterp.service.quantum.QuantumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/quantum")
@CrossOrigin(origins = "*")
public class QuantumController {
    
    @Autowired
    private QuantumService quantumService;
    
    @GetMapping("/keys")
    public ResponseEntity<List<QuantumEncryption>> getAllKeys() {
        return ResponseEntity.ok(quantumService.getAllKeys());
    }
    
    @PostMapping("/keys")
    public ResponseEntity<QuantumEncryption> generateKey(@RequestBody QuantumEncryption key) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quantumService.generateKey(key));
    }
    
    @PutMapping("/keys/{id}/rotate")
    public ResponseEntity<QuantumEncryption> rotateKey(@PathVariable Long id) {
        return ResponseEntity.ok(quantumService.rotateKey(id));
    }
    
    @PutMapping("/keys/{id}/revoke")
    public ResponseEntity<QuantumEncryption> revokeKey(@PathVariable Long id) {
        return ResponseEntity.ok(quantumService.revokeKey(id));
    }
}
