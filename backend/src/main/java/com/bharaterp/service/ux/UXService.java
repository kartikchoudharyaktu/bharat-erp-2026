package com.bharaterp.service.ux;

import com.bharaterp.model.ux.UserPreference;
import com.bharaterp.repository.ux.UXRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UXService {
    
    @Autowired
    private UXRepository uxRepository;
    
    public UserPreference getOrCreatePreferences(Long userId) {
        return uxRepository.findByUserId(userId)
                .orElseGet(() -> {
                    UserPreference prefs = new UserPreference();
                    prefs.setUserId(userId);
                    return uxRepository.save(prefs);
                });
    }
    
    public UserPreference updatePreferences(UserPreference prefs) {
        return uxRepository.save(prefs);
    }
    
    public UserPreference updateTheme(Long userId, String theme) {
        UserPreference prefs = getOrCreatePreferences(userId);
        prefs.setTheme(theme);
        return uxRepository.save(prefs);
    }
    
    public UserPreference updateLanguage(Long userId, String language) {
        UserPreference prefs = getOrCreatePreferences(userId);
        prefs.setLanguage(language);
        return uxRepository.save(prefs);
    }
    
    public UserPreference updateNotifications(Long userId, Boolean email, Boolean push, Boolean sms) {
        UserPreference prefs = getOrCreatePreferences(userId);
        prefs.setEmailNotifications(email);
        prefs.setPushNotifications(push);
        prefs.setSmsNotifications(sms);
        return uxRepository.save(prefs);
    }
}
