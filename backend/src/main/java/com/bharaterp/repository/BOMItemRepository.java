package com.bharaterp.repository;

import com.bharaterp.model.BOMItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BOMItemRepository extends JpaRepository<BOMItem, Long> {
    List<BOMItem> findByBomId(Long bomId);
    List<BOMItem> findByComponentId(Long componentId);
}
