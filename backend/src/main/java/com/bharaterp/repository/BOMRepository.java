package com.bharaterp.repository;

import com.bharaterp.model.BOM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BOMRepository extends JpaRepository<BOM, Long> {
    List<BOM> findByProductId(Long productId);
    List<BOM> findByStatus(String status);
}
