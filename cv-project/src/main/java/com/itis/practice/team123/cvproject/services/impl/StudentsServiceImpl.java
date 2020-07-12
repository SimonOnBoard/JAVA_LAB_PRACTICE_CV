package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.repositories.StudentsRepository;
import com.itis.practice.team123.cvproject.services.interfaces.StudentsService;
import org.springframework.stereotype.Service;

@Service
public class StudentsServiceImpl implements StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentsServiceImpl(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    @Override
    public Student getStudentById(Long id) {
        return studentsRepository.getOne(id);
    }
}
