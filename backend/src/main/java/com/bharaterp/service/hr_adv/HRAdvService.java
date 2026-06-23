package com.bharaterp.service.hr_adv;

import com.bharaterp.model.hr_adv.Recruitment;
import com.bharaterp.model.hr_adv.PerformanceReview;
import com.bharaterp.model.hr_adv.Onboarding;
import com.bharaterp.model.hr_adv.Training;
import com.bharaterp.repository.hr_adv.HRAdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HRAdvService {
    
    @Autowired
    private HRAdvRepository hrAdvRepository;
    
    // ===== Recruitment =====
    public List<Recruitment> getAllRecruitments() {
        return hrAdvRepository.findAll();
    }
    
    public Recruitment createRecruitment(Recruitment recruitment) {
        recruitment.setStatus("OPEN");
        recruitment.setPostingDate(LocalDateTime.now());
        return hrAdvRepository.save(recruitment);
    }
    
    public Recruitment updateApplicationStats(Long id, String statType) {
        Recruitment recruitment = hrAdvRepository.findById(id).orElseThrow();
        switch (statType) {
            case "APPLY": recruitment.setApplications(recruitment.getApplications() + 1); break;
            case "SHORTLIST": recruitment.setShortlisted(recruitment.getShortlisted() + 1); break;
            case "INTERVIEW": recruitment.setInterviewed(recruitment.getInterviewed() + 1); break;
            case "OFFER": recruitment.setOffered(recruitment.getOffered() + 1); break;
            case "HIRE": recruitment.setHired(recruitment.getHired() + 1); break;
        }
        return hrAdvRepository.save(recruitment);
    }
    
    public Recruitment closeRecruitment(Long id) {
        Recruitment recruitment = hrAdvRepository.findById(id).orElseThrow();
        recruitment.setStatus("CLOSED");
        recruitment.setClosingDate(LocalDateTime.now());
        return hrAdvRepository.save(recruitment);
    }
    
    // ===== Performance Review =====
    public List<PerformanceReview> getAllPerformanceReviews() {
        return hrAdvRepository.findAll();
    }
    
    public PerformanceReview createPerformanceReview(PerformanceReview review) {
        review.setTotalScore(
            review.getTechnicalSkills() +
            review.getCommunication() +
            review.getTeamwork() +
            review.getProblemSolving() +
            review.getLeadership() +
            review.getAdaptability() +
            review.getProductivity() +
            review.getQualityOfWork()
        );
        review.setAverageScore(review.getTotalScore() / 8.0);
        
        if (review.getAverageScore() >= 9) review.setRating("OUTSTANDING");
        else if (review.getAverageScore() >= 7) review.setRating("EXCELLENT");
        else if (review.getAverageScore() >= 5) review.setRating("GOOD");
        else if (review.getAverageScore() >= 3) review.setRating("SATISFACTORY");
        else review.setRating("NEEDS_IMPROVEMENT");
        
        return hrAdvRepository.save(review);
    }
    
    public PerformanceReview submitReview(Long id) {
        PerformanceReview review = hrAdvRepository.findById(id).orElseThrow();
        review.setStatus("SUBMITTED");
        return hrAdvRepository.save(review);
    }
    
    // ===== Onboarding =====
    public List<Onboarding> getAllOnboardings() {
        return hrAdvRepository.findAll();
    }
    
    public Onboarding createOnboarding(Onboarding onboarding) {
        onboarding.setOnboardingStatus("NOT_STARTED");
        return hrAdvRepository.save(onboarding);
    }
    
    public Onboarding updateOnboardingStatus(Long id, String status) {
        Onboarding onboarding = hrAdvRepository.findById(id).orElseThrow();
        onboarding.setOnboardingStatus(status);
        return hrAdvRepository.save(onboarding);
    }
    
    // ===== Training =====
    public List<Training> getAllTrainings() {
        return hrAdvRepository.findAll();
    }
    
    public Training createTraining(Training training) {
        training.setStatus("PLANNED");
        return hrAdvRepository.save(training);
    }
    
    public Training scheduleTraining(Long id) {
        Training training = hrAdvRepository.findById(id).orElseThrow();
        training.setStatus("SCHEDULED");
        return hrAdvRepository.save(training);
    }
    
    public Training completeTraining(Long id) {
        Training training = hrAdvRepository.findById(id).orElseThrow();
        training.setStatus("COMPLETED");
        return hrAdvRepository.save(training);
    }
}
