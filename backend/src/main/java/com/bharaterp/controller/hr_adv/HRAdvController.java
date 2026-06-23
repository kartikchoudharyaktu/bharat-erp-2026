package com.bharaterp.controller.hr_adv;

import com.bharaterp.model.hr_adv.Recruitment;
import com.bharaterp.model.hr_adv.PerformanceReview;
import com.bharaterp.model.hr_adv.Onboarding;
import com.bharaterp.model.hr_adv.Training;
import com.bharaterp.service.hr_adv.HRAdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/hr-adv")
@CrossOrigin(origins = "*")
public class HRAdvController {
    
    @Autowired
    private HRAdvService hrAdvService;
    
    // ===== Recruitment APIs =====
    @GetMapping("/recruitment")
    public ResponseEntity<List<Recruitment>> getAllRecruitments() {
        return ResponseEntity.ok(hrAdvService.getAllRecruitments());
    }
    
    @PostMapping("/recruitment")
    public ResponseEntity<Recruitment> createRecruitment(@RequestBody Recruitment recruitment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hrAdvService.createRecruitment(recruitment));
    }
    
    @PutMapping("/recruitment/{id}/apply")
    public ResponseEntity<Recruitment> updateApplicationStats(@PathVariable Long id, @RequestParam String statType) {
        return ResponseEntity.ok(hrAdvService.updateApplicationStats(id, statType));
    }
    
    @PutMapping("/recruitment/{id}/close")
    public ResponseEntity<Recruitment> closeRecruitment(@PathVariable Long id) {
        return ResponseEntity.ok(hrAdvService.closeRecruitment(id));
    }
    
    // ===== Performance Review APIs =====
    @GetMapping("/performance")
    public ResponseEntity<List<PerformanceReview>> getAllPerformanceReviews() {
        return ResponseEntity.ok(hrAdvService.getAllPerformanceReviews());
    }
    
    @PostMapping("/performance")
    public ResponseEntity<PerformanceReview> createPerformanceReview(@RequestBody PerformanceReview review) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hrAdvService.createPerformanceReview(review));
    }
    
    @PutMapping("/performance/{id}/submit")
    public ResponseEntity<PerformanceReview> submitReview(@PathVariable Long id) {
        return ResponseEntity.ok(hrAdvService.submitReview(id));
    }
    
    // ===== Onboarding APIs =====
    @GetMapping("/onboarding")
    public ResponseEntity<List<Onboarding>> getAllOnboardings() {
        return ResponseEntity.ok(hrAdvService.getAllOnboardings());
    }
    
    @PostMapping("/onboarding")
    public ResponseEntity<Onboarding> createOnboarding(@RequestBody Onboarding onboarding) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hrAdvService.createOnboarding(onboarding));
    }
    
    @PutMapping("/onboarding/{id}/status")
    public ResponseEntity<Onboarding> updateOnboardingStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(hrAdvService.updateOnboardingStatus(id, status));
    }
    
    // ===== Training APIs =====
    @GetMapping("/training")
    public ResponseEntity<List<Training>> getAllTrainings() {
        return ResponseEntity.ok(hrAdvService.getAllTrainings());
    }
    
    @PostMapping("/training")
    public ResponseEntity<Training> createTraining(@RequestBody Training training) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hrAdvService.createTraining(training));
    }
    
    @PutMapping("/training/{id}/schedule")
    public ResponseEntity<Training> scheduleTraining(@PathVariable Long id) {
        return ResponseEntity.ok(hrAdvService.scheduleTraining(id));
    }
    
    @PutMapping("/training/{id}/complete")
    public ResponseEntity<Training> completeTraining(@PathVariable Long id) {
        return ResponseEntity.ok(hrAdvService.completeTraining(id));
    }
}
