package com.bharaterp.controller;

import com.bharaterp.model.Product;
import com.bharaterp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    
    @Autowired
    private ProductRepository productRepository;
    
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/code/{code}")
    public ResponseEntity<Product> getProductByCode(@PathVariable String code) {
        return productRepository.findByCode(code)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productRepository.findByNameContaining(keyword);
    }
    
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        try {
            if (productRepository.existsByCode(product.getCode())) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Product code already exists!");
                return ResponseEntity.badRequest().body(error);
            }
            product.setCreatedAt(java.time.LocalDateTime.now());
            Product saved = productRepository.save(product);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            return productRepository.findById(id)
                .map(existing -> {
                    product.setId(id);
                    product.setCreatedAt(existing.getCreatedAt());
                    Product updated = productRepository.save(product);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            if (!productRepository.existsById(id)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Product not found!");
                return ResponseEntity.notFound().build();
            }
            productRepository.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Product deleted successfully!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}