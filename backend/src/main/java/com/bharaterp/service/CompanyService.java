package com.bharaterp.service;

import com.bharaterp.model.Company;
import com.bharaterp.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    
    @Autowired
    private CompanyRepository companyRepository;
    
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
    
    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }
    
    public Optional<Company> getCompanyByCode(String companyCode) {
        return companyRepository.findByCompanyCode(companyCode);
    }
    
    public Company createCompany(Company company) {
        company.setCompanyCode("COMP-" + System.currentTimeMillis());
        return companyRepository.save(company);
    }
    
    public Company updateCompany(Long id, Company companyDetails) {
        Company company = companyRepository.findById(id).orElseThrow();
        company.setCompanyName(companyDetails.getCompanyName());
        company.setGstNo(companyDetails.getGstNo());
        company.setPanNo(companyDetails.getPanNo());
        company.setAddress(companyDetails.getAddress());
        company.setPhone(companyDetails.getPhone());
        company.setEmail(companyDetails.getEmail());
        company.setStatus(companyDetails.getStatus());
        return companyRepository.save(company);
    }
    
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
    
    public List<Company> getCompaniesByStatus(String status) {
        return companyRepository.findByStatus(status);
    }
}
