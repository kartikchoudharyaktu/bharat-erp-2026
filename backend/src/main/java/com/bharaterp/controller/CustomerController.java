package com.bharaterp.controller;

import com.bharaterp.model.Customer;
import com.bharaterp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/code/{code}")
    public ResponseEntity<Customer> getCustomerByCode(@PathVariable String code) {
        return customerRepository.findByCode(code)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public List<Customer> searchCustomers(@RequestParam String keyword) {
        return customerRepository.findByNameContaining(keyword);
    }
    
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        try {
            if (customerRepository.existsByCode(customer.getCode())) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Customer code already exists!");
                return ResponseEntity.badRequest().body(error);
            }
            customer.setCreatedAt(java.time.LocalDateTime.now());
            Customer saved = customerRepository.save(customer);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        try {
            return customerRepository.findById(id)
                .map(existing -> {
                    customer.setId(id);
                    customer.setCreatedAt(existing.getCreatedAt());
                    Customer updated = customerRepository.save(customer);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        try {
            if (!customerRepository.existsById(id)) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "Customer not found!");
                return ResponseEntity.notFound().build();
            }
            customerRepository.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Customer deleted successfully!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}