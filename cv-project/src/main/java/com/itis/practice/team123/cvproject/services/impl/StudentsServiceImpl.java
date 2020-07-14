package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.*;
import com.itis.practice.team123.cvproject.enums.LanguageLevel;
import com.itis.practice.team123.cvproject.models.*;
import com.itis.practice.team123.cvproject.repositories.LanguageRepository;
import com.itis.practice.team123.cvproject.repositories.StudentsRepository;
import com.itis.practice.team123.cvproject.repositories.TagsRepository;
import com.itis.practice.team123.cvproject.services.interfaces.StudentsService;
import com.itis.practice.team123.cvproject.services.interfaces.WeightsAssigner;
import com.itis.practice.team123.cvproject.utils.EducationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentsServiceImpl implements StudentsService {


    private final TagsRepository tagsRepository;
    private final StudentsRepository studentsRepository;
    private final WeightsAssigner weightsAssigner;
    private final LanguageRepository languageRepository;

    @Override
    public Student getStudentById(Long id) {
        return studentsRepository.getOne(id);
    }
    //откромментировать код, переписать через компетенции
    @Override
    public List<WeightedStudentDto> getStudentsByTag(List<String> tagsName) {
        HashMap<Student, Integer> studentsTagCount = new HashMap<>();
        List<Student> students = new ArrayList<>();
        List<Tag> tags = tagsRepository.findAllByNameIn(tagsName);
        for (Tag tag : tags) {
            for (Student student : studentsRepository.findByTag(tag.getId())) {
                Integer k = studentsTagCount.get(student);
                if (k != null) studentsTagCount.put(student, ++k);
                else studentsTagCount.put(student, 1);
            }
        }

        for (Student student : studentsTagCount.keySet()) {
            if (studentsTagCount.get(student) == tagsName.size())
                students.add(student);
        }

        return weightsAssigner.assignStudentWeightsByTags(students, tags);
    }

    @Override
    public void updateStudentBaseInfo(StudentForm studentForm, Long id) {
        Student student = getStudentById(id);
        Student updateStudent = Student.builder()
                .firstName(studentForm.getFirstName())
                .lastName(studentForm.getLastName())
                .patronymic(studentForm.getPatronymic())
                .age(studentForm.getAge())
                .phoneNumber(studentForm.getPhoneNumber())
                .aboutMe(studentForm.getAboutMe())
                .certificates(student.getCertificates())
                .competences(student.getCompetences())
                .education(student.getEducation())
                .languages(student.getLanguages())
                .build();

        studentsRepository.save(updateStudent);
    }

    @Override
    @Transactional
    public void updateStudentCompetencesInfo(TagDto tagDto, Long id) {
        Student student = studentsRepository.getOne(id);
        Tag tag = tagsRepository.findByName(tagDto.getName())
                .orElseThrow(() ->
                    new IllegalArgumentException("Tag with name " + tagDto.getName() + " doesn't exists")
                );

        student.getCompetences().add(Competence.builder()
                .tag(tag)
                .student(student)
                .isConfirmed(false)
                .build()
        );
    }

    @Override
    @Transactional
    public void updateStudentLanguagesInfo(LanguageDto languageDto, Long id) {
        Student student = studentsRepository.getOne(id);
        Language language = languageRepository.findByLevelAndLanguageIgnoreCase(LanguageLevel
                .valueOf(languageDto.getLevel()), languageDto.getName())
                .orElseThrow(() ->
                            new IllegalArgumentException("That language doesn't exists")
                        );

        student.getLanguages().add(language);
    }

    @Override
    @Transactional
    public void updateStudentEducationInfo(EducationDto educationDto, Long id) {
        Student student = studentsRepository.getOne(id);

        student.setEducation(EducationConverter.convert(educationDto));
    }

    @Override
    @Transactional
    public void updateStudentCertificatesInfo(CertificateDto certificateDto, Long id) {
        Student student = studentsRepository.getOne(id);

        student.getCertificates().add(Certificate.builder()
                .description(certificateDto.getName())
                .yearOfReceipt(certificateDto.getYear())
                .student(student)
                .build()
        );
    }
}
