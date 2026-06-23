package com.bharaterp.repository.blockchain;

import com.bharaterp.model.blockchain.BlockchainLedger;
import com.bharaterp.model.blockchain.SmartContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BlockchainRepository extends JpaRepository<BlockchainLedger, Long> {
    
    // Blockchain Ledger Queries
    List<BlockchainLedger> findByTransactionType(String transactionType);
    List<BlockchainLedger> findByStatus(String status);
    List<BlockchainLedger> findBySender(String sender);
    List<BlockchainLedger> findByReceiver(String receiver);
    List<BlockchainLedger> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    
    // Smart Contract Queries
    List<SmartContract> findByContractType(String contractType);
    List<SmartContract> findByStatus(String status);
    List<SmartContract> findByCreator(String creator);
}
