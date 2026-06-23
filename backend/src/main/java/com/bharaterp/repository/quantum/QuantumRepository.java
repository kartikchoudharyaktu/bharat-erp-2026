package com.bharaterp.repository.quantum;

import com.bharaterp.model.quantum.QuantumEncryption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuantumRepository extends JpaRepository<QuantumEncryption, Long> {
    
    List<QuantumEncryption> findByKeyType(String keyType);
    List<QuantumEncryption> findByStatus(String status);
    List<QuantumEncryption> findByQuantumResistance(String quantumResistance);
    List<QuantumEncryption> findByExpiryDateBefore(LocalDateTime date);
}
