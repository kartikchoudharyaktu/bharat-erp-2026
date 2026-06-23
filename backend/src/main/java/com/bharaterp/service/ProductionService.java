package com.bharaterp.service;

import com.bharaterp.model.ProductionOrder;
import com.bharaterp.repository.ProductionOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductionService {
    
    @Autowired
    private ProductionOrderRepository productionOrderRepository;
    
    public List<ProductionOrder> getAllProductionOrders() {
        return productionOrderRepository.findAll();
    }
    
    public Optional<ProductionOrder> getProductionOrderById(Long id) {
        return productionOrderRepository.findById(id);
    }
    
    public ProductionOrder createProductionOrder(ProductionOrder productionOrder) {
        productionOrder.setOrderNumber("PROD-" + System.currentTimeMillis());
        return productionOrderRepository.save(productionOrder);
    }
    
    public ProductionOrder updateProductionOrder(Long id, ProductionOrder orderDetails) {
        ProductionOrder order = productionOrderRepository.findById(id).orElseThrow();
        order.setProductId(orderDetails.getProductId());
        order.setQuantity(orderDetails.getQuantity());
        order.setStartDate(orderDetails.getStartDate());
        order.setEndDate(orderDetails.getEndDate());
        order.setStatus(orderDetails.getStatus());
        order.setPriority(orderDetails.getPriority());
        return productionOrderRepository.save(order);
    }
    
    public void deleteProductionOrder(Long id) {
        productionOrderRepository.deleteById(id);
    }
    
    public List<ProductionOrder> getProductionOrdersByStatus(String status) {
        return productionOrderRepository.findByStatus(status);
    }
    
    public List<ProductionOrder> getProductionOrdersByProduct(Long productId) {
        return productionOrderRepository.findByProductId(productId);
    }
}
