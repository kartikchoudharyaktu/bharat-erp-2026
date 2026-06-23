package com.bharaterp.controller;

import com.bharaterp.service.AIEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AIController {

    @Autowired
    private AIEngine aiEngine;

    @PostMapping("/chat")
    public Map<String, Object> chat(@RequestBody Map<String, String> request) {
        return aiEngine.chat(request.get("query"));
    }

    @GetMapping("/search")
    public Map<String, Object> search(@RequestParam String keyword) {
        return aiEngine.smartSearch(keyword);
    }

    @PostMapping("/auto-invoice")
    public Map<String, Object> autoInvoice(@RequestBody Map<String, Object> request) {
        String customerName = (String) request.get("customerName");
        java.util.List<Map<String, Object>> items = (java.util.List<Map<String, Object>>) request.get("items");
        return aiEngine.autoGenerateInvoice(customerName, items);
    }

    @GetMapping("/anomalies")
    public Map<String, Object> detectAnomalies() {
        return aiEngine.detectAnomalies();
    }

    @GetMapping("/predict")
    public Map<String, Object> predict(@RequestParam(defaultValue = "7") int days) {
        return aiEngine.predictSales(days);
    }

    @PostMapping("/scan-qr")
    public Map<String, Object> scanQR(@RequestBody Map<String, String> request) {
        return aiEngine.scanQR(request.get("data"));
    }

    @PostMapping("/run-agents")
    public Map<String, Object> runAgents() {
        return aiEngine.runAgents();
    }

    @PostMapping("/extract")
    public Map<String, Object> extract(@RequestBody Map<String, String> request) {
        return aiEngine.extractData(request.get("text"));
    }
}