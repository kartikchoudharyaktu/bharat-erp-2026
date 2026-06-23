package com.bharaterp.service;

import com.bharaterp.model.PurchaseOrder;
import com.bharaterp.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {
    
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }
    
    public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id);
    }
    
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        purchaseOrder.setPoNumber("PO-" + System.currentTimeMillis());
        return purchaseOrderRepository.save(purchaseOrder);
    }
    
    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder poDetails) {
        PurchaseOrder po = purchaseOrderRepository.findById(id).orElseThrow();
        po.setVendorId(poDetails.getVendorId());
        po.setOrderDate(poDetails.getOrderDate());
        po.setDeliveryDate(poDetails.getDeliveryDate());
        po.setTotal(poDetails.getTotal());
        po.setStatus(poDetails.getStatus());
        return purchaseOrderRepository.save(po);
    }
    
    public void deletePurchaseOrder(Long id) {
        purchaseOrderRepository.deleteById(id);
    }
}
