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
import java.util.Objects;
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
    public List<WeightedStudentDto> getStudentsByFilters(FilterFormData filterFormData) throws IllegalArgumentException{
        List<String> dataLanguage = filterFormData.getLanguage();

        List<Student> students;
        List<Student> studentsTags;
        List<Tag> tags;
        if (filterFormData.getComp() != null && filterFormData.getComp().size() != 0) {
            tags = tagsRepository.findAllByNameIn(filterFormData.getComp());
            studentsTags = this.getStudentsByTag(filterFormData.getComp());
        }
        else  {
            studentsTags = studentsRepository.findAll();
            tags = tagsRepository.findAll();
        }

        List<Language> languages = new ArrayList<>();
        if (dataLanguage != null && dataLanguage.size() != 0) {
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

        if (filterFormData.getEducation() != null && filterFormData.getEducation().size() != 0) {
            students = students.stream().filter(student ->
                    Objects.nonNull(student.getEducation()))
                    .collect(Collectors.toList());
            students = students.stream().filter(student ->
                    filterFormData.getEducation().contains(student.getEducation().name()))
                    .collect(Collectors.toList());
        }
        return weightsAssigner.assignStudentWeightsByTags(students, tags);
    }

}
