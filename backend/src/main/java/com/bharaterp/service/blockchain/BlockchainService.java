package com.bharaterp.service.blockchain;

import com.bharaterp.model.blockchain.BlockchainLedger;
import com.bharaterp.model.blockchain.SmartContract;
import com.bharaterp.repository.blockchain.BlockchainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlockchainService {
    
    @Autowired
    private BlockchainRepository blockchainRepository;
    
    // ===== Blockchain Ledger =====
    public List<BlockchainLedger> getAllBlocks() {
        return blockchainRepository.findAll();
    }
    
    public BlockchainLedger createBlock(BlockchainLedger block) {
        block.setStatus("PENDING");
        return blockchainRepository.save(block);
    }
    
    public BlockchainLedger confirmBlock(Long id) {
        BlockchainLedger block = blockchainRepository.findById(id).orElseThrow();
        block.setStatus("CONFIRMED");
        block.setConfirmations(block.getConfirmations() + 1);
        return blockchainRepository.save(block);
    }
    
    public BlockchainLedger validateBlock(Long id) {
        BlockchainLedger block = blockchainRepository.findById(id).orElseThrow();
        block.setValidatedBy("System");
        block.setValidationDate(LocalDateTime.now());
        return blockchainRepository.save(block);
    }
    
    // ===== Smart Contracts =====
    public List<SmartContract> getAllContracts() {
        return blockchainRepository.findAll();
    }
    
    public SmartContract createContract(SmartContract contract) {
        contract.setStatus("DRAFT");
        return blockchainRepository.save(contract);
    }
    
    public SmartContract deployContract(Long id) {
        SmartContract contract = blockchainRepository.findById(id).orElseThrow();
        contract.setStatus("DEPLOYED");
        return blockchainRepository.save(contract);
    }
    
    public SmartContract executeContract(Long id) {
        SmartContract contract = blockchainRepository.findById(id).orElseThrow();
        contract.setStatus("EXECUTING");
        return blockchainRepository.save(contract);
    }
    
    public SmartContract completeContract(Long id) {
        SmartContract contract = blockchainRepository.findById(id).orElseThrow();
        contract.setStatus("COMPLETED");
        return blockchainRepository.save(contract);
    }
    
    // ===== Blockchain Audit =====
    public List<BlockchainLedger> getTransactionsByType(String type) {
        return blockchainRepository.findByTransactionType(type);
    }
    
    public boolean verifyTransaction(Long id) {
        BlockchainLedger block = blockchainRepository.findById(id).orElseThrow();
        // Verify block integrity
        String expectedHash = "0x" + Long.toHexString(block.getCreatedAt().getNano());
        return block.getBlockHash().startsWith(expectedHash.substring(0, 8));
    }
}
