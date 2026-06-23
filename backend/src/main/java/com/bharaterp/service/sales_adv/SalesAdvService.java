package com.bharaterp.service.sales_adv;

import com.bharaterp.model.sales_adv.CRM;
import com.bharaterp.model.sales_adv.ECommerceIntegration;
import com.bharaterp.model.sales_adv.MarketingAutomation;
import com.bharaterp.model.sales_adv.AfterSalesService;
import com.bharaterp.repository.sales_adv.SalesAdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SalesAdvService {
    
    @Autowired
    private SalesAdvRepository salesAdvRepository;
    
    // ===== CRM =====
    public List<CRM> getAllCRMInteractions() {
        return salesAdvRepository.findAll();
    }
    
    public CRM createCRMInteraction(CRM crm) {
        crm.setStatus("OPEN");
        return salesAdvRepository.save(crm);
    }
    
    public CRM updateCRMStatus(Long id, String status) {
        CRM crm = salesAdvRepository.findById(id).orElseThrow();
        crm.setStatus(status);
        if (status.equals("RESOLVED") || status.equals("CLOSED")) {
            crm.setResolutionDate(LocalDateTime.now().toString());
        }
        return salesAdvRepository.save(crm);
    }
    
    // ===== E-Commerce =====
    public List<ECommerceIntegration> getAllECommerceOrders() {
        return salesAdvRepository.findAll();
    }
    
    public ECommerceIntegration createECommerceOrder(ECommerceIntegration order) {
        if (order.getOrderNumber() == null) {
            order.setOrderNumber("ECO-" + System.currentTimeMillis());
        }
        order.setSyncStatus("PENDING");
        return salesAdvRepository.save(order);
    }
    
    public ECommerceIntegration syncOrder(Long id) {
        ECommerceIntegration order = salesAdvRepository.findById(id).orElseThrow();
        order.setSyncStatus("SYNCED");
        order.setSyncDate(LocalDateTime.now());
        return salesAdvRepository.save(order);
    }
    
    // ===== Marketing Automation =====
    public List<MarketingAutomation> getAllCampaigns() {
        return salesAdvRepository.findAll();
    }
    
    public MarketingAutomation createCampaign(MarketingAutomation campaign) {
        campaign.setStatus("DRAFT");
        return salesAdvRepository.save(campaign);
    }
    
    public MarketingAutomation scheduleCampaign(Long id) {
        MarketingAutomation campaign = salesAdvRepository.findById(id).orElseThrow();
        campaign.setStatus("SCHEDULED");
        campaign.setScheduledTime(LocalDateTime.now());
        return salesAdvRepository.save(campaign);
    }
    
    public MarketingAutomation sendCampaign(Long id) {
        MarketingAutomation campaign = salesAdvRepository.findById(id).orElseThrow();
        campaign.setStatus("SENT");
        campaign.setSentTime(LocalDateTime.now());
        return salesAdvRepository.save(campaign);
    }
    
    // ===== After-Sales =====
    public List<AfterSalesService> getAllServices() {
        return salesAdvRepository.findAll();
    }
    
    public AfterSalesService createService(AfterSalesService service) {
        if (service.getServiceNumber() == null) {
            service.setServiceNumber("SVC-" + System.currentTimeMillis());
        }
        service.setStatus("OPEN");
        return salesAdvRepository.save(service);
    }
    
    public AfterSalesService assignService(Long id, String assignedTo) {
        AfterSalesService service = salesAdvRepository.findById(id).orElseThrow();
        service.setStatus("ASSIGNED");
        service.setAssignedTo(assignedTo);
        return salesAdvRepository.save(service);
    }
    
    public AfterSalesService completeService(Long id) {
        AfterSalesService service = salesAdvRepository.findById(id).orElseThrow();
        service.setStatus("RESOLVED");
        service.setCompletionDate(LocalDateTime.now().toString());
        return salesAdvRepository.save(service);
    }
}
