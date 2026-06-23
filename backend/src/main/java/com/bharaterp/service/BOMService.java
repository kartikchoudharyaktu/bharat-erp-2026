package com.bharaterp.service;

import com.bharaterp.model.BOM;
import com.bharaterp.repository.BOMRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BOMService {
    
    @Autowired
    private BOMRepository bomRepository;
    
    public List<BOM> getAllBOMs() {
        return bomRepository.findAll();
    }
    
    public Optional<BOM> getBOMById(Long id) {
        return bomRepository.findById(id);
    }
    
    public BOM createBOM(BOM bom) {
        return bomRepository.save(bom);
    }
    
    public BOM updateBOM(Long id, BOM bomDetails) {
        BOM bom = bomRepository.findById(id).orElseThrow();
        bom.setProductId(bomDetails.getProductId());
        bom.setBomName(bomDetails.getBomName());
        bom.setVersion(bomDetails.getVersion());
        bom.setStatus(bomDetails.getStatus());
        return bomRepository.save(bom);
    }
    
    public void deleteBOM(Long id) {
        bomRepository.deleteById(id);
    }
    
    public List<BOM> getBOMsByProduct(Long productId) {
        return bomRepository.findByProductId(productId);
    }
}
