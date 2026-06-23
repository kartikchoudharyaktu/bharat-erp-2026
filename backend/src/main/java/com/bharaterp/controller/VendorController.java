package com.bharaterp.controller;

import com.bharaterp.model.Vendor;
import com.bharaterp.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@CrossOrigin(origins = "*")
public class VendorController {
    
    @Autowired
    private VendorRepository vendorRepository;
    
    @GetMapping
    public List<Vendor> getAll() {
        return vendorRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getById(@PathVariable Long id) {
        return vendorRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Vendor create(@RequestBody Vendor vendor) {
        vendor.setCreatedAt(LocalDateTime.now());
        return vendorRepository.save(vendor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Vendor> update(@PathVariable Long id, @RequestBody Vendor vendor) {
        return vendorRepository.findById(id).map(existing -> {
            vendor.setId(id);
            return ResponseEntity.ok(vendorRepository.save(vendor));
        }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        vendorRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}