package com.bharaterp.service.quantum;

import com.bharaterp.model.quantum.QuantumEncryption;
import com.bharaterp.repository.quantum.QuantumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuantumService {
    
    @Autowired
    private QuantumRepository quantumRepository;
    
    public List<QuantumEncryption> getAllKeys() {
        return quantumRepository.findAll();
    }
    
    public QuantumEncryption generateKey(QuantumEncryption key) {
        key.setStatus("ACTIVE");
        key.setGenerationDate(LocalDateTime.now());
        key.setExpiryDate(LocalDateTime.now().plusDays(30));
        return quantumRepository.save(key);
    }
    
    public QuantumEncryption rotateKey(Long id) {
        QuantumEncryption key = quantumRepository.findById(id).orElseThrow();
        key.setStatus("ROTATED");
        // Generate new key
        key.setEncryptionKey("Q" + Long.toHexString(System.currentTimeMillis()) + 
                            Long.toHexString(System.nanoTime()));
        key.setGenerationDate(LocalDateTime.now());
        key.setExpiryDate(LocalDateTime.now().plusDays(30));
        return quantumRepository.save(key);
    }
    
    public QuantumEncryption revokeKey(Long id) {
        QuantumEncryption key = quantumRepository.findById(id).orElseThrow();
        key.setStatus("REVOKED");
        return quantumRepository.save(key);
    }
}
