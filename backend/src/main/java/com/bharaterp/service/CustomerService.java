package com.bharaterp.service;

import com.bharaterp.model.Customer;
import com.bharaterp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        customer.setCustomerName(customerDetails.getCustomerName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhone(customerDetails.getPhone());
        customer.setAddress(customerDetails.getAddress());
        customer.setGstNo(customerDetails.getGstNo());
        customer.setPanNo(customerDetails.getPanNo());
        return customerRepository.save(customer);
    }
    
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    
    public List<Customer> searchCustomers(String keyword) {
        // Custom search logic
        return customerRepository.findAll();
    }
}
