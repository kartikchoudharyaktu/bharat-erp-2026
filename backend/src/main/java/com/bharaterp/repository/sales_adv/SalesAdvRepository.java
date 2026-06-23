package com.bharaterp.repository.sales_adv;

import com.bharaterp.model.sales_adv.CRM;
import com.bharaterp.model.sales_adv.ECommerceIntegration;
import com.bharaterp.model.sales_adv.MarketingAutomation;
import com.bharaterp.model.sales_adv.AfterSalesService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SalesAdvRepository extends JpaRepository<CRM, Long> {
    
    // CRM Queries
    List<CRM> findByCustomerId(Long customerId);
    List<CRM> findByStatus(String status);
    List<CRM> findByInteractionType(String interactionType);
    List<CRM> findByPriority(String priority);
    
    // E-Commerce Queries
    List<ECommerceIntegration> findByPlatform(String platform);
    List<ECommerceIntegration> findByOrderStatus(String orderStatus);
    List<ECommerceIntegration> findByCustomerId(Long customerId);
    
    // Marketing Automation Queries
    List<MarketingAutomation> findByCampaignType(String campaignType);
    List<MarketingAutomation> findByStatus(String status);
    
    // After-Sales Queries
    List<AfterSalesService> findByCustomerId(Long customerId);
    List<AfterSalesService> findByServiceType(String serviceType);
    List<AfterSalesService> findByStatus(String status);
}
