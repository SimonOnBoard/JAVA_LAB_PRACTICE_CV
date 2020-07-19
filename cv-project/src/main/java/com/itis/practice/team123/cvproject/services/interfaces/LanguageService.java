package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.models.Language;

public interface LanguageService {
    Language initializeLanguage(Language language);

    Language getLanguage(Long language);

    Language getLanguageByNameAndLevel(String lang);
}
