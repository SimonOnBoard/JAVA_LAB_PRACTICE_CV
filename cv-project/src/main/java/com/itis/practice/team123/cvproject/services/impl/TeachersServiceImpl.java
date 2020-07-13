package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.TeacherEditForm;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.repositories.TeachersRepository;
import com.itis.practice.team123.cvproject.services.interfaces.LanguageService;
import com.itis.practice.team123.cvproject.services.interfaces.TeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TeachersServiceImpl implements TeachersService {
    private final TeachersRepository teachersRepository;
    private final LanguageService languageService;

    @Override
    public void updateTeacher(TeacherEditForm teacherEditForm, Long id) throws IllegalArgumentException {
        Teacher teacher = getTeacher(id);
        teacher.setAdditionalInfo(teacherEditForm.getInfo());
        teacher.setName(teacherEditForm.getName());
        teacher.setPatronymic(teacherEditForm.getPatronymic());
        teacher.setInstitution(teacherEditForm.getInstitution());
        teacher.setSurname(teacherEditForm.getSurname());
        teachersRepository.saveAndFlush(teacher);
    }

    @Override
    public Teacher getTeacher(Long id) throws IllegalArgumentException {
        return teachersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    @Transactional
    public void addLanguage(Long id, Language language) throws IllegalArgumentException {
        Teacher teacher = this.getTeacher(id);
        Language language1 = languageService.initializeLanguage(language);
        removeIfExists(teacher.getLanguages(), language1);
        teacher.getLanguages().add(language1);
    }

    @Override
    @Transactional
    public void removeLanguage(Long id, Long language) throws IllegalArgumentException {
        Teacher teacher = this.getTeacher(id);
        Language language1 = languageService.getLanguage(language);
        teacher.getLanguages().remove(language1);
        teachersRepository.saveAndFlush(teacher);

    }

    private boolean removeIfExists(List<Language> languages, Language languageToRemove) {
        boolean result = false;
        for (Language language : languages) {
            //подумать над случаями апперкейся
            if (language.getLanguage().toUpperCase().equals(languageToRemove.getLanguage().toUpperCase())) {
                languages.remove(languageToRemove);
                result = true;
                break;
            }
        }
        return result;
    }
}
