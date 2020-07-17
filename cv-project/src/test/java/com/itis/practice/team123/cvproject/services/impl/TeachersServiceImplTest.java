package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.TeacherEditForm;
import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.enums.LanguageLevel;
import com.itis.practice.team123.cvproject.enums.Role;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.repositories.TeachersRepository;
import com.itis.practice.team123.cvproject.services.interfaces.LanguageService;
import com.itis.practice.team123.cvproject.services.interfaces.TeachersService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

class TeachersServiceImplTest {
    @Mock
    private TeachersRepository teachersRepository;
    @Mock
    private LanguageService languageService;

    private TeachersService teachersService;

    public TeachersServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        this.teachersService = new TeachersServiceImpl(teachersRepository, languageService);
    }

    @Nested
    class TestTeacherServiceGetTeacher {
        private Teacher teacher;
        private ArgumentCaptor<Long> argumentCaptor;

        @BeforeEach
        public void init() {
            teacher = new Teacher(1L, "Simon", "12345", Role.TEACHER, "aaa@gmail.com");
            argumentCaptor = ArgumentCaptor.forClass(Long.class);
        }

        @Test
        void getTeacherSuccess() {
            given(teachersRepository.findById(anyLong())).willReturn(Optional.of(teacher));
            assertThat(teachersService.getTeacher(1L)).isEqualTo(teacher);
            verify(teachersRepository).findById(argumentCaptor.capture());
            assertThat(1L).isEqualTo(argumentCaptor.getValue());
        }

        @Test
        void getTeacherException() {
            given(teachersRepository.findById(anyLong())).willReturn(Optional.empty());
            assertThrows(IllegalArgumentException.class, () -> teachersService.getTeacher(1L));
            verify(teachersRepository).findById(argumentCaptor.capture());
            assertThat(1L).isEqualTo(argumentCaptor.getValue());
        }
    }

    @Nested
    class TestTeacherServiceUpdateTeacher {
        private Teacher teacher;
        private ArgumentCaptor<Long> argumentCaptor;
        private TeacherEditForm teacherEditForm;
        private ArgumentCaptor<Teacher> teacherArgumentCaptor;

        @BeforeEach
        public void init() {
            teacher = new Teacher(1L, "Simon", "12345", Role.TEACHER, "aaa@gmail.com");
            argumentCaptor = ArgumentCaptor.forClass(Long.class);
            teacherEditForm = TeacherEditForm.builder()
                    .name("Almazzz")
                    .patronymic("Ildarovich")
                    .surname("Shangareev")
                    .institution("Kazan Federal")
                    .info("null")
                    .build();
            teacherArgumentCaptor = ArgumentCaptor.forClass(Teacher.class);
        }

        @Test
        void updateTeacherByFormAndIdFail() {
            given(teachersRepository.findById(anyLong())).willReturn(Optional.empty());
            assertThrows(IllegalArgumentException.class, () -> teachersService.updateTeacher(teacherEditForm, 1L));
            verify(teachersRepository).findById(argumentCaptor.capture());
            assertThat(1L).isEqualTo(argumentCaptor.getValue());
        }

        @Test
        void updateTeacherByFormAndTeacherFail() {
            given(teachersRepository.save(anyObject())).willThrow(RuntimeException.class);
            assertThrows(RuntimeException.class, () -> teachersService.updateTeacher(teacherEditForm, teacher));
            checkTeachersInfo();
        }

        void checkTeachersInfo() {
            verify(teachersRepository).save(teacherArgumentCaptor.capture());
            Teacher capturedTeacher = teacherArgumentCaptor.getValue();
            assertThat(capturedTeacher.getName()).isEqualTo(teacherEditForm.getName());
            assertThat(capturedTeacher.getPatronymic()).isEqualTo(teacherEditForm.getPatronymic());
            assertThat(capturedTeacher.getAdditionalInfo()).isEqualTo(teacherEditForm.getInfo());
            assertThat(capturedTeacher.getSurname()).isEqualTo(teacherEditForm.getSurname());
            assertThat(capturedTeacher.getInstitution()).isEqualTo(teacherEditForm.getInstitution());
        }

        @Test
        void updateTeacherByFormAndIdSuccess() {
            given(teachersRepository.findById(anyLong())).willReturn(Optional.of(teacher));
            teachersService.updateTeacher(teacherEditForm, 1L);
            verify(teachersRepository).findById(argumentCaptor.capture());
            assertThat(1L).isEqualTo(argumentCaptor.getValue());
            checkTeachersInfo();
        }

        @Test
        void updateTeacherByFormAndTeacherSuccess() {
            teachersService.updateTeacher(teacherEditForm, teacher);
            checkTeachersInfo();
        }
    }

    @Nested
    class TestTeacherServiceAddAndRemoveLanguage {
        private Teacher teacher;
        private ArgumentCaptor<Long> argumentCaptor;
        private ArgumentCaptor<Language> languageArgumentCaptor;
        private ArgumentCaptor<Teacher> teacherArgumentCaptor;

        private Language language;
        private Language languageToReturn;

        @BeforeEach
        public void init() {
            teacher = new Teacher(1L, "Simon", "12345", Role.TEACHER, "aaa@gmail.com");
            argumentCaptor = ArgumentCaptor.forClass(Long.class);
            language = Language.builder().language("English").level(LanguageLevel.C2).build();
            languageToReturn = Language.builder().id(1L).language("English").level(LanguageLevel.C2).build();
            languageArgumentCaptor = ArgumentCaptor.forClass(Language.class);
            teacherArgumentCaptor = ArgumentCaptor.forClass(Teacher.class);
        }

        @Test
        void addLanguageByIdAndLanguageFail() {
            given(teachersRepository.findById(anyLong())).willReturn(Optional.empty());
            assertThrows(IllegalArgumentException.class, () -> teachersService.addLanguage(1L, language));
            verify(teachersRepository).findById(argumentCaptor.capture());
            assertThat(1L).isEqualTo(argumentCaptor.getValue());
        }

        @Test
        void addLanguageByIdAndLanguageSuccess() {
            given(teachersRepository.findById(anyLong())).willReturn(Optional.of(teacher));
            given(teachersRepository.save(any(Teacher.class))).willReturn(teacher);
            given(languageService.initializeLanguage(any(Language.class))).willReturn(languageToReturn);
            teachersService.addLanguage(1L, language);
            checkFinalSaveInfoAndLanguageServiceArguments();
        }


        @Test
        void addLanguageByTeacherAndLanguageFail() {
            given(teachersRepository.save(any(Teacher.class))).willThrow(RuntimeException.class);
            given(languageService.initializeLanguage(any(Language.class))).willReturn(languageToReturn);
            assertThrows(RuntimeException.class, () -> teachersService.addLanguage(teacher, language));
            checkFinalSaveInfoAndLanguageServiceArguments();
        }

        @Test
        void addLanguageByTeacherAndLanguageSuccess() {
            given(teachersRepository.save(any(Teacher.class))).willReturn(teacher);
            given(languageService.initializeLanguage(any(Language.class))).willReturn(languageToReturn);
            teachersService.addLanguage(teacher, language);
            checkFinalSaveInfoAndLanguageServiceArguments();
        }

        private void checkFinalSaveInfoAndLanguageServiceArguments(){
//            verify(teachersRepository).save(teacherArgumentCaptor.capture());
            verify(languageService).initializeLanguage(languageArgumentCaptor.capture());
            assertThat(languageArgumentCaptor.getValue()).isEqualTo(language);
            language.setId(1L);
            teacher.getLanguages().add(language);
//            assertThat(teacherArgumentCaptor.getValue()).isEqualTo(teacher);
        }
    }

    @Nested
    class TestTeacherServiceRemoveLanguage {
        private Teacher teacher;
        private ArgumentCaptor<Long> argumentCaptor;
        private ArgumentCaptor<Teacher> teacherArgumentCaptor;
        private Language languageToReturn;

        @BeforeEach
        public void init() {
            teacher = new Teacher(1L, "Simon", "12345", Role.TEACHER, "aaa@gmail.com");
            teacher.setLanguages(new ArrayList<>());
            argumentCaptor = ArgumentCaptor.forClass(Long.class);
            languageToReturn = Language.builder().id(1L).language("English").level(LanguageLevel.C2).build();
            teacherArgumentCaptor = ArgumentCaptor.forClass(Teacher.class);
        }

        @Test
        void removeLanguageGetTeacherExceptionByTeacherIdAndLanguageId() {
            given(teachersRepository.findById(anyLong())).willThrow(IllegalArgumentException.class);
            assertThrows(IllegalArgumentException.class, () -> teachersService.removeLanguage(1L,1L));
            verify(teachersRepository).findById(argumentCaptor.capture());
            assertThat(1L).isEqualTo(argumentCaptor.getValue());
        }

        @Test
        void removeLanguageGetTeacherExceptionByTeacherAndLanguageId() {
            given(teachersRepository.findById(anyLong())).willThrow(IllegalArgumentException.class);
            assertThrows(IllegalArgumentException.class, () -> teachersService.removeLanguage(teacher,1L));
            verify(teachersRepository).findById(argumentCaptor.capture());
            assertThat(1L).isEqualTo(argumentCaptor.getValue());
        }

        private void checkValues() {
            verify(languageService).getLanguage(argumentCaptor.capture());
            assertThat(argumentCaptor.getValue()).isEqualTo(1L);
        }

        private void initMocks() {
            given(teachersRepository.findById(anyLong())).willReturn(Optional.ofNullable(teacher));
            given(languageService.getLanguage(anyLong())).willReturn(languageToReturn);
            teacher.getLanguages().add(languageToReturn);
        }


        @Test
        void removeLanguageByTeacherAndLanguageIdSuccess() {
            initMocks();
            teachersService.removeLanguage(teacher,1L);
            checkValues();
        }
    }
}