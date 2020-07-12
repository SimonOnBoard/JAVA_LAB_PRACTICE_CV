package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.TeacherProfileInfo;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Teacher;

public interface TeachersService {
    void updateTeacher(TeacherProfileInfo teacherProfileInfo, Long id) throws IllegalArgumentException;
    Teacher getTeacher(Long id) throws IllegalArgumentException;

    void addLanguage(Long id, Language language) throws IllegalArgumentException;
    void removeLanguage(Long id, Long language) throws IllegalArgumentException;
}
