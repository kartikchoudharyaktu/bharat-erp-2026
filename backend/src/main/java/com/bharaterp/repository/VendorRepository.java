package com.bharaterp.repository;

import com.bharaterp.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByCode(String code);
    List<Vendor> findByNameContaining(String name);
    boolean existsByCode(String code);
}