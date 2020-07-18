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
    private final LanguageRepository languageRepository;

    @Override
    public Student getStudentById(Long id) {
        return studentsRepository.getOne(id);
    }

    //откромментировать код, переписать через компетенции
    @Override
    public List<Student> getStudentsByTag(List<String> tagsName) {
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

//        return weightsAssigner.assignStudentWeightsByTags(students, tags);
        return students;
    }

    @Override
    public List<WeightedStudentDto> getStudentsByFilters(FilterFormData filterFormData) {
        List<String> languages = filterFormData.getLanguage();
//        Education education = Education.valueOf(filterFormData.getEducation().get(0));
        List<Student> students;
        if (languages != null) {
            students = studentsRepository.findAllByLanguagesInAndEducation(
                    languageRepository.findAllByLanguageIn(languages),
                    Education.valueOf(filterFormData.getEducation().get(0)));
        }
        else {
            students = studentsRepository.findAll();
        }
        List<Student> studentsTags;
        List<Tag> tags;
        if (filterFormData.getComp() != null) {
             tags = tagsRepository.findAllByNameIn(filterFormData.getComp());
            studentsTags = this.getStudentsByTag(filterFormData.getComp());
        }

        else  {
            studentsTags = students;
            tags = tagsRepository.findAll();
        }
        studentsTags = studentsTags.stream().filter(students::contains).collect(Collectors.toList());
        return weightsAssigner.assignStudentWeightsByTags(studentsTags, tags);
    }

    @Override
    public List<TagDto> getTagsForStudent(Student student) {
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
    public void updateStudentBaseInfo(StudentForm studentForm, Long id) {
        Student student = getStudentById(id);

        student.setFirstName(studentForm.getFirstName());
        student.setLastName(studentForm.getLastName());
        student.setPatronymic(studentForm.getPatronymic());
        student.setAge(studentForm.getAge());
        student.setPhoneNumber(studentForm.getPhoneNumber());
        student.setEmail(studentForm.getEmail());
        student.setAboutMe(studentForm.getAboutMe());

        studentsRepository.save(student);
    }

    @Override
    @Transactional
    public void updateStudentCompetencesInfo(TagDto tagDto, Long id) {
        Student student = studentsRepository.getOne(id);
        Tag tag = tagsRepository.findByName(tagDto.getName())
                .orElseThrow(() ->
                        new IllegalArgumentException("Tag with name " + tagDto.getName() + " doesn't exists")
                );
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
    public void updateStudentLanguagesInfo(Language languageToAdd, Long id) {
        Student student = studentsRepository.getOne(id);
        Language language = languageService.initializeLanguage(languageToAdd);
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
        Certificate certificate = Certificate.builder()
                .description(certificateDto.getName())
                .yearOfReceipt(certificateDto.getYear())
                .student(student)
                .build();
        certificateRepository.save(certificate);
        student.getCertificates().add(certificate);
    }
}
