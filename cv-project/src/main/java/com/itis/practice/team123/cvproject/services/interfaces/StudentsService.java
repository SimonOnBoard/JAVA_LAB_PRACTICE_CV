package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.*;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Student;

import java.util.List;

public interface StudentsService {

    Student getStudentById(Long id);

    List<TagDto> getTagsForStudent(Student student);

    void updateStudentBaseInfo(StudentForm studentForm, Long id);
    void updateStudentCompetencesInfo(TagDto tagDto, Long id);
    void updateStudentLanguagesInfo(Language languageDto, Long id);
    void updateStudentEducationInfo(EducationDto educationDto, Long id);
    void updateStudentCertificatesInfo(CertificateDto certificateDto, Long id);
}
