package com.bharaterp.repository;

import com.bharaterp.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCode(String code);
    List<Customer> findByNameContaining(String name);
    List<Customer> findByIsActiveTrue();
    boolean existsByCode(String code);
}