package com.itis.practice.team123.cvproject.repositories;

import com.itis.practice.team123.cvproject.CvProjectApplication;
import com.itis.practice.team123.cvproject.CvProjectApplicationTests;
import com.itis.practice.team123.cvproject.enums.LanguageLevel;
import com.itis.practice.team123.cvproject.models.Language;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

class LanguageRepositoryTest {
    private LanguageRepository languageRepository;

    @BeforeEach
    void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CvProjectApplicationTests.class);
        languageRepository = context.getBean(LanguageRepository.class);
        DataSource dataSource = context.getBean("testDataSourceForJpa", DataSource.class);
    }

    @Test
    @Transactional
    void save() {
        languageRepository.save(Language.builder()
                .language("Simple language")
                .level(LanguageLevel.B2)
                .build());
        assertEquals(1,languageRepository.findAll().size());
    }
}