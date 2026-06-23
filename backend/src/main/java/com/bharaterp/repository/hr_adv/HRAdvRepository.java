package com.bharaterp.repository.hr_adv;

import com.bharaterp.model.hr_adv.Recruitment;
import com.bharaterp.model.hr_adv.PerformanceReview;
import com.bharaterp.model.hr_adv.Onboarding;
import com.bharaterp.model.hr_adv.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HRAdvRepository extends JpaRepository<Recruitment, Long> {
    
    // Recruitment Queries
    List<Recruitment> findByDepartment(String department);
    List<Recruitment> findByStatus(String status);
    List<Recruitment> findByJobType(String jobType);
    List<Recruitment> findByExperienceLevel(String experienceLevel);
    
    // Performance Review Queries
    List<PerformanceReview> findByEmployeeId(Long employeeId);
    List<PerformanceReview> findByRating(String rating);
    List<PerformanceReview> findByReviewPeriod(String reviewPeriod);
    
    // Onboarding Queries
    List<Onboarding> findByEmployeeId(Long employeeId);
    List<Onboarding> findByOnboardingStatus(String status);
    
    // Training Queries
    List<Training> findByTrainingType(String trainingType);
    List<Training> findByStatus(String status);
    List<Training> findBySkillLevel(String skillLevel);
}
