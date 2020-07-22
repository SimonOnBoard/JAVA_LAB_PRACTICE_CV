package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.*;
import com.itis.practice.team123.cvproject.enums.Education;
import com.itis.practice.team123.cvproject.models.*;
import com.itis.practice.team123.cvproject.repositories.*;
import com.itis.practice.team123.cvproject.services.interfaces.LanguageService;
import com.itis.practice.team123.cvproject.services.interfaces.StudentsService;
import com.itis.practice.team123.cvproject.services.interfaces.WeightsAssigner;
import com.itis.practice.team123.cvproject.utils.EducationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
//целиком надо рефакторить разносить в следующей коммите
public class StudentsServiceImpl implements StudentsService {


    private final TagsRepository tagsRepository;
    private final StudentsRepository studentsRepository;
    private final WeightsAssigner weightsAssigner;
    private final LanguageService languageService;
    private final CompetenceRepository competenceRepository;
    private final CertificateRepository certificateRepository;

    @Override
    public Student getStudentById(Long id) {
        return studentsRepository.getOne(id);
    }

    @Override
    public List<TagDto> getTagsAvaliableToAdd(Student student) {
        student = getStudentById(student.getId());
        List<Tag> studentTags = student.getCompetences().stream()
                .map(Competence::getTag)
                .collect(Collectors.toList());
        List<Tag> allTags = tagsRepository.findAll();
        allTags.removeAll(studentTags);
        return allTags.stream()
                .map(TagDto::from)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateStudentBaseInfo(StudentForm studentForm, Long id) {
        Student student = getStudentById(id);
        student.setFirstName(studentForm.getFirstName());
        student.setLastName(studentForm.getLastName());
        student.setPatronymic(studentForm.getPatronymic());
        student.setAge(studentForm.getAge());
        student.setPhoneNumber(studentForm.getPhoneNumber());
        student.setEmail(studentForm.getEmail());
        student.setAboutMe(studentForm.getAboutMe());
    }

    @Override
    @Transactional
    public void addCompetence(TagDto tagDto, Long id) {
        Student student = getStudentById(id);
        Tag tag = tagsRepository.findByName(tagDto.getName())
                .orElseThrow(() -> new IllegalArgumentException("Tag with name " + tagDto.getName() + " doesn't exists"));
        Competence competence = Competence.builder()
                .tag(tag)
                .student(student)
                .isConfirmed(false)
                .build();
        competenceRepository.save(competence);
        student.getCompetences().add(competence);
    }

    @Override
    @Transactional
    public void addLanguage(Language languageToAdd, Long id) {
        Student student = getStudentById(id);
        Language language = languageService.initializeLanguage(languageToAdd);
        Pair<Boolean, Boolean> result = languageService
                .checkAndRemoveIfHasTheSameLanguageWithAnotherLevel(student.getLanguages(), language);
        if(result.getFirst() || !result.getSecond() ) {
            student.getLanguages().add(language);
        }
    }

    @Override
    @Transactional
    public void updateStudentEducationInfo(EducationDto educationDto, Long id) {
        Student student = studentsRepository.getOne(id);
        student.setEducation(EducationConverter.convert(educationDto));
    }

    @Override
    @Transactional
    public void addCertificates(CertificateDto certificateDto, Long id) {
        Student student = getStudentById(id);
        Certificate certificate = Certificate.builder()
                .description(certificateDto.getName())
                .yearOfReceipt(certificateDto.getYear())
                .student(student)
                .build();
        certificateRepository.save(certificate);
        student.getCertificates().add(certificate);
    }
}
