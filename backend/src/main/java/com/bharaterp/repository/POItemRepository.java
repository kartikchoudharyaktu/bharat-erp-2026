package com.bharaterp.repository;

import com.bharaterp.model.POItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface POItemRepository extends JpaRepository<POItem, Long> {
    List<POItem> findByPoId(Long poId);
    void deleteByPoId(Long poId);
}