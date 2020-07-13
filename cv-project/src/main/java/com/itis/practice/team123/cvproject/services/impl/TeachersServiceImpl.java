package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.TeacherProfileInfo;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.repositories.TeachersRepository;
import com.itis.practice.team123.cvproject.services.interfaces.LanguageService;
import com.itis.practice.team123.cvproject.services.interfaces.TeachersService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TeachersServiceImpl implements TeachersService {
    private TeachersRepository teachersRepository;
    private LanguageService languageService;

    public TeachersServiceImpl(TeachersRepository teachersRepository, LanguageService languageService) {
        this.teachersRepository = teachersRepository;
        this.languageService = languageService;
    }

    @Override
    public void updateTeacher(TeacherProfileInfo teacherProfileInfo, Long id) throws IllegalArgumentException {
        Teacher teacher = getTeacher(id);
        teacher.setAdditionalInfo(teacherProfileInfo.getInfo());
        teacher.setName(teacherProfileInfo.getName());
        teacher.setPatronymic(teacherProfileInfo.getPatronymic());
        teacher.setInstitution(teacherProfileInfo.getInstitution());
        teacher.setSurname(teacherProfileInfo.getSurname());
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
