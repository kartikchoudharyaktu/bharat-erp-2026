package com.bharaterp.service;

import com.bharaterp.model.Product;
import com.bharaterp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
    
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setProductName(productDetails.getProductName());
        product.setProductCode(productDetails.getProductCode());
        product.setCategory(productDetails.getCategory());
        product.setPrice(productDetails.getPrice());
        product.setQuantity(productDetails.getQuantity());
        return productRepository.save(product);
    }
    
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    
    public List<Product> searchProducts(String keyword) {
        return productRepository.findAll();
    }
}
