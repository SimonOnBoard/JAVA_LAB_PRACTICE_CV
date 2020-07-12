package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.repositories.StudentsRepository;
import com.itis.practice.team123.cvproject.repositories.TagsRepository;
import com.itis.practice.team123.cvproject.services.interfaces.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    TagsRepository tagsRepository;

    private final StudentsRepository studentsRepository;

    public StudentsServiceImpl(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public Student getStudentById(Long id) {
        return studentsRepository.getOne(id);
    }

    @Override
    public List<Student> getStudentsByTag(List<String> tagsName) {
        HashMap<Student, Integer> studentsTagCount = new HashMap<>();
        List<Student> students = new ArrayList<>();
        for (String tagName : tagsName) {
            Tag tag = tagsRepository.findByName(tagName).get();
            for (Long id : studentsRepository.findByTag(tag.getId())) {
                Student student = studentsRepository.getOne(id);
                Integer k = studentsTagCount.get(student);
                if (k != null)
                    studentsTagCount.put(student, ++k);
                else studentsTagCount.put(student, 1);

            }
        }

        for (Student student : studentsTagCount.keySet()) {
            if (studentsTagCount.get(student) == tagsName.size())
                students.add(student);
        }
        return students;
    }
}
