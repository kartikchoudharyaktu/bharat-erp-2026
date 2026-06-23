package com.bharaterp.repository;

import com.bharaterp.model.ProductionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductionOrderRepository extends JpaRepository<ProductionOrder, Long> {
    List<ProductionOrder> findByProductId(Long productId);
    List<ProductionOrder> findByStatus(String status);
    List<ProductionOrder> findByPriority(String priority);
}
