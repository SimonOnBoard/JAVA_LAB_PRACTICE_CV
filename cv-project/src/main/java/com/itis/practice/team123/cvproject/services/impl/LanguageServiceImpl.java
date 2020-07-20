package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.enums.LanguageLevel;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.repositories.LanguageRepository;
import com.itis.practice.team123.cvproject.services.interfaces.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    public Language initializeLanguage(Language language) {
        Language resultLanguage = languageRepository.findByLevelAndLanguageIgnoreCase(language.getLevel(), language.getLanguage()).orElse(null);
        if(resultLanguage == null) {
            language.setId(null);
            language.setLanguage(language.getLanguage().toLowerCase());
            resultLanguage = languageRepository.save(language);
        }
        return resultLanguage;
    }

    @Override
    public Language getLanguage(Long language) {
        return languageRepository.findById(language).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Language getLanguageByNameAndLevel(String lang) throws IllegalArgumentException {
        String[] langArray = lang.split(" ");
        return languageRepository
                .findByLevelAndLanguageIgnoreCase(LanguageLevel.valueOf(langArray[1]),
                        langArray[0]).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll() ;
    }
}
