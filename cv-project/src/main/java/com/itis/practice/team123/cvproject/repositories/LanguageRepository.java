package com.itis.practice.team123.cvproject.repositories;

import com.itis.practice.team123.cvproject.enums.LanguageLevel;
import com.itis.practice.team123.cvproject.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByLevelAndLanguageIgnoreCase(LanguageLevel level, String language);
    List<Language> findAllByLanguageIn(List<String> languages);
}
