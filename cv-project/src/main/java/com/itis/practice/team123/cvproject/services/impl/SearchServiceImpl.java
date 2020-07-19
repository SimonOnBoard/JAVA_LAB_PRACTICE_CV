package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.FilterFormData;
import com.itis.practice.team123.cvproject.dto.WeightedStudentDto;
import com.itis.practice.team123.cvproject.enums.Education;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.repositories.*;
import com.itis.practice.team123.cvproject.services.interfaces.LanguageService;
import com.itis.practice.team123.cvproject.services.interfaces.SearchService;
import com.itis.practice.team123.cvproject.services.interfaces.WeightsAssigner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {
    private final TagsRepository tagsRepository;
    private final StudentsRepository studentsRepository;
    private final WeightsAssigner weightsAssigner;
    private final LanguageService languageService;

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

        return students;
    }

    @Override
    public List<WeightedStudentDto> getStudentsByFilters(FilterFormData filterFormData) {
        List<String> dataLanguage = filterFormData.getLanguage();
        List<Language> languages = new ArrayList<>();
        List<Student> students;
        List<Student> studentsTags;
        List<Tag> tags;
        if (filterFormData.getComp() != null) {
            tags = tagsRepository.findAllByNameIn(filterFormData.getComp());
            studentsTags = this.getStudentsByTag(filterFormData.getComp());
        }

        else  {
            studentsTags = studentsRepository.findAll();
            tags = tagsRepository.findAll();
        }

        if (dataLanguage != null) {
            for (String lang : dataLanguage) {
                languages.add(languageService.getLanguageByNameAndLevel(lang));
            }
            students = studentsTags.stream().filter(student ->
                    student.getLanguages().containsAll(languages))
                    .collect(Collectors.toList());

        }
        else {
            students = studentsTags;
        }
        if (filterFormData.getEducation() != null) {
            students = students.stream().filter(student ->
                    student.getEducation().equals(Education
                            .valueOf(filterFormData.getEducation().get(0))))
                    .collect(Collectors.toList());
        }
        return weightsAssigner.assignStudentWeightsByTags(students, tags);
    }

}
