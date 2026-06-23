package com.bharaterp.service.sales;

import com.bharaterp.model.sales.SalesOrder;
import com.bharaterp.repository.sales.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Service
public class SalesService {
    
    @Autowired
    private SalesRepository salesRepository;
    
    public List<SalesOrder> getAllOrders() {
        return salesRepository.findAll();
    }
    
    public Optional<SalesOrder> getOrderById(Long id) {
        return salesRepository.findById(id);
    }
    
    public SalesOrder createOrder(SalesOrder order) {
        if (order.getOrderNumber() == null || order.getOrderNumber().isEmpty()) {
            order.setOrderNumber("SO-" + System.currentTimeMillis());
        }
        return salesRepository.save(order);
    }
    
    public SalesOrder updateOrder(Long id, SalesOrder orderDetails) {
        SalesOrder order = salesRepository.findById(id).orElseThrow();
        order.setCustomerId(orderDetails.getCustomerId());
        order.setCustomerName(orderDetails.getCustomerName());
        order.setOrderDate(orderDetails.getOrderDate());
        order.setDeliveryDate(orderDetails.getDeliveryDate());
        order.setSubtotal(orderDetails.getSubtotal());
        order.setTaxAmount(orderDetails.getTaxAmount());
        order.setDiscountAmount(orderDetails.getDiscountAmount());
        order.setTotalAmount(orderDetails.getTotalAmount());
        order.setOrderStatus(orderDetails.getOrderStatus());
        order.setPaymentStatus(orderDetails.getPaymentStatus());
        order.setPaymentMethod(orderDetails.getPaymentMethod());
        return salesRepository.save(order);
    }
    
    public SalesOrder updateStatus(Long id, String status) {
        SalesOrder order = salesRepository.findById(id).orElseThrow();
        order.setOrderStatus(status);
        return salesRepository.save(order);
    }
    
    public SalesOrder updatePaymentStatus(Long id, String status) {
        SalesOrder order = salesRepository.findById(id).orElseThrow();
        order.setPaymentStatus(status);
        return salesRepository.save(order);
    }
    
    public void deleteOrder(Long id) {
        salesRepository.deleteById(id);
    }
    
    // Reports
    public List<SalesOrder> getOrdersByCustomer(Long customerId) {
        return salesRepository.findByCustomerId(customerId);
    }
    
    public List<SalesOrder> getOrdersByStatus(String status) {
        return salesRepository.findByOrderStatus(status);
    }
    
    public BigDecimal getTotalSales(LocalDateTime start, LocalDateTime end) {
        return salesRepository.findByOrderDateBetween(start, end)
                .stream()
                .filter(o -> "DELIVERED".equals(o.getOrderStatus()) || "CONFIRMED".equals(o.getOrderStatus()))
                .map(SalesOrder::getTotalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
