package com.bharaterp.model.innovation;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bharat_language_ai")
public class BharatLanguageAI {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String languageCode; // HI, EN, BN, TA, TE, ML, KN, UR, GU, MR, PA, OR, AS, etc.
    
    @Column(nullable = false)
    private String languageName;
    
    private String script; // Devanagari, Latin, Bengali, Tamil, etc.
    
    private Boolean isSupported = true;
    
    private String translationModel;
    private String speechModel;
    private String nlpModel;
    
    private String voiceGender; // MALE, FEMALE, NEUTRAL
    
    private String voiceName;
    private String voiceProvider; // Google, Microsoft, Amazon, BharatAI
    
    private String configuration; // JSON
    
    private Long companyId;
    
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getLanguageCode() { return languageCode; }
    public void setLanguageCode(String languageCode) { this.languageCode = languageCode; }
    
    public String getLanguageName() { return languageName; }
    public void setLanguageName(String languageName) { this.languageName = languageName; }
    
    public String getScript() { return script; }
    public void setScript(String script) { this.script = script; }
    
    public Boolean getIsSupported() { return isSupported; }
    public void setIsSupported(Boolean isSupported) { this.isSupported = isSupported; }
    
    public String getTranslationModel() { return translationModel; }
    public void setTranslationModel(String translationModel) { this.translationModel = translationModel; }
    
    public String getSpeechModel() { return speechModel; }
    public void setSpeechModel(String speechModel) { this.speechModel = speechModel; }
    
    public String getNlpModel() { return nlpModel; }
    public void setNlpModel(String nlpModel) { this.nlpModel = nlpModel; }
    
    public String getVoiceGender() { return voiceGender; }
    public void setVoiceGender(String voiceGender) { this.voiceGender = voiceGender; }
    
    public String getVoiceName() { return voiceName; }
    public void setVoiceName(String voiceName) { this.voiceName = voiceName; }
    
    public String getVoiceProvider() { return voiceProvider; }
    public void setVoiceProvider(String voiceProvider) { this.voiceProvider = voiceProvider; }
    
    public String getConfiguration() { return configuration; }
    public void setConfiguration(String configuration) { this.configuration = configuration; }
    
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
