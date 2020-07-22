package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.TeacherDto;
import com.itis.practice.team123.cvproject.dto.TeacherEditForm;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Teacher;

public interface TeachersService {
    TeacherDto updateTeacher(TeacherEditForm teacherEditForm, Long id) throws IllegalArgumentException;
    TeacherDto updateTeacher(TeacherEditForm teacherEditForm, Teacher teacher);

    Teacher getTeacher(Long id) throws IllegalArgumentException;

    Language addLanguage(Long id, Language language) throws IllegalArgumentException;
    Language addLanguage(Teacher teacher, Language language);

    Language removeLanguage(Long id, Long language) throws IllegalArgumentException;
    Language removeLanguage(Teacher teacher, Long language) throws IllegalArgumentException;

}
