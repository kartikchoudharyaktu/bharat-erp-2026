package com.bharaterp.controller.blockchain;

import com.bharaterp.model.blockchain.BlockchainLedger;
import com.bharaterp.model.blockchain.SmartContract;
import com.bharaterp.service.blockchain.BlockchainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/blockchain")
@CrossOrigin(origins = "*")
public class BlockchainController {
    
    @Autowired
    private BlockchainService blockchainService;
    
    // ===== Blockchain Ledger APIs =====
    @GetMapping("/ledger")
    public ResponseEntity<List<BlockchainLedger>> getAllBlocks() {
        return ResponseEntity.ok(blockchainService.getAllBlocks());
    }
    
    @PostMapping("/ledger")
    public ResponseEntity<BlockchainLedger> createBlock(@RequestBody BlockchainLedger block) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blockchainService.createBlock(block));
    }
    
    @PutMapping("/ledger/{id}/confirm")
    public ResponseEntity<BlockchainLedger> confirmBlock(@PathVariable Long id) {
        return ResponseEntity.ok(blockchainService.confirmBlock(id));
    }
    
    @PutMapping("/ledger/{id}/validate")
    public ResponseEntity<BlockchainLedger> validateBlock(@PathVariable Long id) {
        return ResponseEntity.ok(blockchainService.validateBlock(id));
    }
    
    @GetMapping("/ledger/verify/{id}")
    public ResponseEntity<Boolean> verifyTransaction(@PathVariable Long id) {
        return ResponseEntity.ok(blockchainService.verifyTransaction(id));
    }
    
    // ===== Smart Contract APIs =====
    @GetMapping("/contracts")
    public ResponseEntity<List<SmartContract>> getAllContracts() {
        return ResponseEntity.ok(blockchainService.getAllContracts());
    }
    
    @PostMapping("/contracts")
    public ResponseEntity<SmartContract> createContract(@RequestBody SmartContract contract) {
        return ResponseEntity.status(HttpStatus.CREATED).body(blockchainService.createContract(contract));
    }
    
    @PutMapping("/contracts/{id}/deploy")
    public ResponseEntity<SmartContract> deployContract(@PathVariable Long id) {
        return ResponseEntity.ok(blockchainService.deployContract(id));
    }
    
    @PutMapping("/contracts/{id}/execute")
    public ResponseEntity<SmartContract> executeContract(@PathVariable Long id) {
        return ResponseEntity.ok(blockchainService.executeContract(id));
    }
    
    @PutMapping("/contracts/{id}/complete")
    public ResponseEntity<SmartContract> completeContract(@PathVariable Long id) {
        return ResponseEntity.ok(blockchainService.completeContract(id));
    }
}
