package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.WeightedStudentDto;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.repositories.StudentsRepository;
import com.itis.practice.team123.cvproject.repositories.TagsRepository;
import com.itis.practice.team123.cvproject.services.interfaces.StudentsService;
import com.itis.practice.team123.cvproject.services.interfaces.WeightsAssigner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentsServiceImpl implements StudentsService {


    private final TagsRepository tagsRepository;
    private final StudentsRepository studentsRepository;
    private final WeightsAssigner weightsAssigner;


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

        List<WeightedStudentDto> weightedStudents = weightsAssigner.assignStudentWeightsByTags(students, tags);


        return weightedStudents;
    }
}
