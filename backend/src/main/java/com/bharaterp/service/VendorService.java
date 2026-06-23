package com.bharaterp.service;

import com.bharaterp.model.Vendor;
import com.bharaterp.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VendorService {
    
    @Autowired
    private VendorRepository vendorRepository;
    
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
    
    public Optional<Vendor> getVendorById(Long id) {
        return vendorRepository.findById(id);
    }
    
    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }
    
    public Vendor updateVendor(Long id, Vendor vendorDetails) {
        Vendor vendor = vendorRepository.findById(id).orElseThrow();
        vendor.setVendorName(vendorDetails.getVendorName());
        vendor.setEmail(vendorDetails.getEmail());
        vendor.setPhone(vendorDetails.getPhone());
        vendor.setAddress(vendorDetails.getAddress());
        vendor.setGstNo(vendorDetails.getGstNo());
        return vendorRepository.save(vendor);
    }
    
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}
