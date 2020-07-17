package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.TeacherEditForm;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.repositories.TeachersRepository;
import com.itis.practice.team123.cvproject.services.interfaces.LanguageService;
import com.itis.practice.team123.cvproject.services.interfaces.TeachersService;
import com.itis.practice.team123.cvproject.utils.Initializer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeachersServiceImpl implements TeachersService {
    private final TeachersRepository teachersRepository;
    private final LanguageService languageService;

    @Override
    public void updateTeacher(TeacherEditForm teacherEditForm, Long id) throws IllegalArgumentException {
        Teacher teacher = getTeacher(id);
        updateTeacher(teacherEditForm, teacher);
    }

    @Override
    public void updateTeacher(TeacherEditForm teacherEditForm, Teacher teacher) {
        teacher.setAdditionalInfo(teacherEditForm.getInfo());
        teacher.setName(teacherEditForm.getName());
        teacher.setPatronymic(teacherEditForm.getPatronymic());
        teacher.setInstitution(teacherEditForm.getInstitution());
        teacher.setSurname(teacherEditForm.getSurname());
        teacher = teachersRepository.save(teacher);
    }

    @Override
    public Teacher getTeacher(Long id) throws IllegalArgumentException {
        return teachersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public void addLanguage(Long id, Language language) throws IllegalArgumentException {
        Teacher teacher = this.getTeacher(id);
        addLanguage(teacher, language);
    }

    //переписать к чертям собачим
    @Override
    public void addLanguage(Teacher teacher, Language languageToAdd) {
        Language language = languageService.initializeLanguage(languageToAdd);
        if (teacher.getLanguages() == null) teacher.setLanguages(new ArrayList<>());
        boolean deleted = checkAndReplace(teacher.getLanguages(), language);
        if (!deleted) {
            teacher = teachersRepository.save(teacher);
            teacher.getLanguages().add(language);
        }
        teacher = teachersRepository.save(teacher);
    }

    @Override
    public void removeLanguage(Long id, Long language) throws IllegalArgumentException {
        removeLanguage(new Teacher((Long) id), language);
    }

    @Override
    @Transactional
    public void removeLanguage(Teacher teacher, Long languageToDelete) throws IllegalArgumentException {
        teacher = this.getTeacher(teacher.getId());
        Language language = languageService.getLanguage(languageToDelete);
        teacher.getLanguages().remove(language);
    }


    //придумать тесты?
    private boolean checkAndReplace(List<Language> languages, Language languageToRemove) {
        boolean result = false;
        for (Language language : languages) {
            //подумать над случаями апперкейся
            if (language.getLanguage().toUpperCase().equals(languageToRemove.getLanguage().toUpperCase())) {
                if (!languageToRemove.getLevel().equals(language.getLevel())) {
                    languages.remove(language);
                    languages.add(languageToRemove);
                }
                result = true;
                break;
            }
        }
        return result;
    }
}
