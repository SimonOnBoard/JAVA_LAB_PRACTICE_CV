package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.enums.LanguageLevel;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.repositories.LanguageRepository;
import com.itis.practice.team123.cvproject.services.interfaces.LanguageService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

class LanguageServiceImplTest {
    @Mock
    private LanguageRepository languageRepository;

    private LanguageService languageService;

    public LanguageServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        this.languageService = new LanguageServiceImpl(languageRepository);
    }


    @Test
    void initializeLanguageSuccess() {

    }

    @Test
    void getLanguageThrows() {
        given(languageRepository.findById(anyLong())).willReturn(Optional.empty());
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        assertThrows(IllegalArgumentException.class, () -> languageService.getLanguage(1L));
        verify(languageRepository).findById(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(1L);
    }

    @Test
    void getLanguageSuccess() {
        Language language = new Language((Long) null, "English", LanguageLevel.C2);
        given(languageRepository.findById(anyLong())).willReturn(Optional.ofNullable(language));
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass(Long.class);
        Language languageReceived = languageService.getLanguage(1L);
        verify(languageRepository).findById(argumentCaptor.capture());
        assertThat(argumentCaptor.getValue()).isEqualTo(1L);
        assertThat(languageReceived).isEqualToComparingFieldByField(language);
    }
}