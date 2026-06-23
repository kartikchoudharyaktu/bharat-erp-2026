package com.bharaterp.controller.ux;

import com.bharaterp.model.ux.UserPreference;
import com.bharaterp.service.ux.UXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ux")
@CrossOrigin(origins = "*")
public class UXController {
    
    @Autowired
    private UXService uxService;
    
    @GetMapping("/preferences/{userId}")
    public ResponseEntity<UserPreference> getPreferences(@PathVariable Long userId) {
        return ResponseEntity.ok(uxService.getOrCreatePreferences(userId));
    }
    
    @PutMapping("/preferences")
    public ResponseEntity<UserPreference> updatePreferences(@RequestBody UserPreference prefs) {
        return ResponseEntity.ok(uxService.updatePreferences(prefs));
    }
    
    @PutMapping("/preferences/{userId}/theme")
    public ResponseEntity<UserPreference> updateTheme(@PathVariable Long userId, @RequestParam String theme) {
        return ResponseEntity.ok(uxService.updateTheme(userId, theme));
    }
    
    @PutMapping("/preferences/{userId}/language")
    public ResponseEntity<UserPreference> updateLanguage(@PathVariable Long userId, @RequestParam String language) {
        return ResponseEntity.ok(uxService.updateLanguage(userId, language));
    }
    
    @PutMapping("/preferences/{userId}/notifications")
    public ResponseEntity<UserPreference> updateNotifications(
            @PathVariable Long userId,
            @RequestParam Boolean email,
            @RequestParam Boolean push,
            @RequestParam Boolean sms) {
        return ResponseEntity.ok(uxService.updateNotifications(userId, email, push, sms));
    }
}
