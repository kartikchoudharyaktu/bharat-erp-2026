package com.bharaterp.controller.inventory;

import com.bharaterp.model.inventory.ProductInventory;
import com.bharaterp.service.inventory.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;
    
    // Product APIs
    @GetMapping("/products")
    public ResponseEntity<List<ProductInventory>> getAllProducts() {
        return ResponseEntity.ok(inventoryService.getAllProducts());
    }
    
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductInventory> getProductById(@PathVariable Long id) {
        return inventoryService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/products/code/{code}")
    public ResponseEntity<ProductInventory> getProductByCode(@PathVariable String code) {
        return inventoryService.getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/products")
    public ResponseEntity<ProductInventory> createProduct(@RequestBody ProductInventory product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createProduct(product));
    }
    
    @PutMapping("/products/{id}")
    public ResponseEntity<ProductInventory> updateProduct(@PathVariable Long id, @RequestBody ProductInventory product) {
        return ResponseEntity.ok(inventoryService.updateProduct(id, product));
    }
    
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        inventoryService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    
    // Stock APIs
    @PutMapping("/products/{id}/stock")
    public ResponseEntity<ProductInventory> updateStock(
            @PathVariable Long id,
            @RequestParam Double quantity,
            @RequestParam String movementType) {
        return ResponseEntity.ok(inventoryService.updateStock(id, quantity, movementType));
    }
    
    // Report APIs
    @GetMapping("/reports/low-stock")
    public ResponseEntity<List<ProductInventory>> getLowStock() {
        return ResponseEntity.ok(inventoryService.getLowStockItems());
    }
    
    @GetMapping("/reports/critical-stock")
    public ResponseEntity<List<ProductInventory>> getCriticalStock() {
        return ResponseEntity.ok(inventoryService.getCriticalStockItems());
    }
    
    @GetMapping("/reports/category/{category}")
    public ResponseEntity<List<ProductInventory>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(inventoryService.getProductsByCategory(category));
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ProductInventory>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(inventoryService.searchProducts(keyword));
    }
}
