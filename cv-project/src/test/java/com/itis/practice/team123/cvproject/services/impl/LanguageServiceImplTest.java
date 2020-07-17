package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.enums.LanguageLevel;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.repositories.LanguageRepository;
import com.itis.practice.team123.cvproject.services.interfaces.LanguageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class LanguageServiceImplTest {
    @Mock
    private LanguageRepository languageRepository;

    private LanguageService languageService;

    public LanguageServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        this.languageService = new LanguageServiceImpl(languageRepository);
    }


    @org.junit.jupiter.api.Nested
    class GetLanguageInitializationTests {
        private ArgumentCaptor<Long> argumentCaptor;
        private Language languageToCheck;
        private ArgumentCaptor<LanguageLevel> levelCapture;
        private ArgumentCaptor<String> stringArgumentCaptor;
        private Language languageToReturn;
        private ArgumentCaptor<Language> languageCaptor;

        @BeforeEach
        public void init() {
            stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
            levelCapture = ArgumentCaptor.forClass(LanguageLevel.class);
            languageToCheck = new Language(null, "English", LanguageLevel.C2);
            languageToReturn = new Language(8L, "English", LanguageLevel.C2);
            languageCaptor = ArgumentCaptor.forClass(Language.class);
        }

        @Test
        void initializeLanguageByLevelAndNameSuccess() {
            given(languageRepository.findByLevelAndLanguageIgnoreCase(anyObject(), anyString()))
                    .willReturn(Optional.of(languageToCheck));
            //ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
            assertThat(languageService.initializeLanguage(languageToCheck)).isEqualTo(languageToCheck);
            verify(languageRepository).findByLevelAndLanguageIgnoreCase(levelCapture.capture(), stringArgumentCaptor.capture());
            assertThat(languageToCheck.getLevel()).isEqualTo(levelCapture.getValue());
            assertThat(languageToCheck.getLanguage()).isEqualTo(stringArgumentCaptor.getValue());

        }

        @Test
        void initializeLanguageByInsertingNewSuccess() {
            given(languageRepository.findByLevelAndLanguageIgnoreCase(anyObject(), anyString()))
                    .willReturn(Optional.empty());
            given(languageRepository.save(anyObject()))
                    .willReturn(languageToReturn);
            assertThat(languageService.initializeLanguage(languageToCheck)).isEqualTo(languageToReturn);
            verify(languageRepository).save(languageCaptor.capture());
            assertThat(languageCaptor.getValue()).isEqualTo(languageToCheck);
            verify(languageRepository).findByLevelAndLanguageIgnoreCase(levelCapture.capture(), stringArgumentCaptor.capture());
            assertThat(languageToCheck.getLevel()).isEqualTo(levelCapture.getValue());
            assertThat(languageToCheck.getLanguage()).isEqualTo(stringArgumentCaptor.getValue());
        }
    }

    @org.junit.jupiter.api.Nested
    class GetLanguageTests {
        private ArgumentCaptor<Long> argumentCaptor;
        private Language language;

        @BeforeEach
        public void init() {
            argumentCaptor = ArgumentCaptor.forClass(Long.class);
            language = new Language((Long) null, "English", LanguageLevel.C2);
        }


        @Test
        void getLanguageThrows() {
            given(languageRepository.findById(anyLong())).willReturn(Optional.empty());
            assertThrows(IllegalArgumentException.class, () -> languageService.getLanguage(1L));
            verify(languageRepository).findById(argumentCaptor.capture());
            assertThat(argumentCaptor.getValue()).isEqualTo(1L);
        }

        @Test
        void getLanguageSuccess() {
            given(languageRepository.findById(anyLong())).willReturn(Optional.ofNullable(language));
            Language languageReceived = languageService.getLanguage(1L);
            verify(languageRepository).findById(argumentCaptor.capture());
            assertThat(argumentCaptor.getValue()).isEqualTo(1L);
            assertThat(languageReceived).isEqualToComparingFieldByField(language);
        }
    }
}