package com.bharaterp.service.inventory;

import com.bharaterp.model.inventory.ProductInventory;
import com.bharaterp.model.inventory.StockMovement;
import com.bharaterp.model.inventory.Warehouse;
import com.bharaterp.model.inventory.BatchTracking;
import com.bharaterp.repository.inventory.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    
    @Autowired
    private InventoryRepository inventoryRepository;
    
    // Product Inventory Operations
    public List<ProductInventory> getAllProducts() {
        return inventoryRepository.findAll();
    }
    
    public Optional<ProductInventory> getProductById(Long id) {
        return inventoryRepository.findById(id);
    }
    
    public Optional<ProductInventory> getProductByCode(String code) {
        return inventoryRepository.findByProductCode(code);
    }
    
    public ProductInventory createProduct(ProductInventory product) {
        if (product.getProductCode() == null || product.getProductCode().isEmpty()) {
            product.setProductCode("PRD-" + System.currentTimeMillis());
        }
        return inventoryRepository.save(product);
    }
    
    public ProductInventory updateProduct(Long id, ProductInventory productDetails) {
        ProductInventory product = inventoryRepository.findById(id).orElseThrow();
        product.setProductName(productDetails.getProductName());
        product.setCategory(productDetails.getCategory());
        product.setSubCategory(productDetails.getSubCategory());
        product.setBrand(productDetails.getBrand());
        product.setUnit(productDetails.getUnit());
        product.setPurchasePrice(productDetails.getPurchasePrice());
        product.setSellingPrice(productDetails.getSellingPrice());
        product.setMrp(productDetails.getMrp());
        product.setCurrentStock(productDetails.getCurrentStock());
        product.setMinStock(productDetails.getMinStock());
        product.setMaxStock(productDetails.getMaxStock());
        product.setReorderLevel(productDetails.getReorderLevel());
        product.setHsnCode(productDetails.getHsnCode());
        product.setGstRate(productDetails.getGstRate());
        product.setWarehouseLocation(productDetails.getWarehouseLocation());
        product.setRackNumber(productDetails.getRackNumber());
        product.setBinNumber(productDetails.getBinNumber());
        product.setDescription(productDetails.getDescription());
        product.setStatus(productDetails.getStatus());
        product.setBarcode(productDetails.getBarcode());
        product.setRfidTag(productDetails.getRfidTag());
        product.setIsBatchTracked(productDetails.getIsBatchTracked());
        product.setIsSerialTracked(productDetails.getIsSerialTracked());
        product.setIsExpiryTracked(productDetails.getIsExpiryTracked());
        return inventoryRepository.save(product);
    }
    
    @Transactional
    public ProductInventory updateStock(Long productId, Double quantity, String movementType) {
        ProductInventory product = inventoryRepository.findById(productId).orElseThrow();
        Double previousStock = product.getCurrentStock();
        Double newStock = movementType.equals("SALE") ? previousStock - quantity : previousStock + quantity;
        product.setCurrentStock(newStock);
        
        // Create stock movement record
        StockMovement movement = new StockMovement();
        movement.setProductId(productId);
        movement.setMovementType(movementType);
        movement.setQuantity(quantity);
        movement.setPreviousStock(previousStock);
        movement.setNewStock(newStock);
        // Save movement (you would have a StockMovementRepository)
        
        return inventoryRepository.save(product);
    }
    
    public void deleteProduct(Long id) {
        inventoryRepository.deleteById(id);
    }
    
    // Stock Reports
    public List<ProductInventory> getLowStockItems() {
        return inventoryRepository.findLowStockItems();
    }
    
    public List<ProductInventory> getCriticalStockItems() {
        return inventoryRepository.findCriticalStockItems();
    }
    
    public List<ProductInventory> getProductsByCategory(String category) {
        return inventoryRepository.findByCategory(category);
    }
    
    public List<ProductInventory> searchProducts(String keyword) {
        return inventoryRepository.findByProductNameContainingIgnoreCase(keyword);
    }
    
    // Warehouse Operations
    // (These would be implemented with WarehouseRepository)
    
    // Batch Tracking Operations
    // (These would be implemented with BatchTrackingRepository)
}
