package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.WorkDto;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/works")
public class StudiesIntegrationAPI {
    @Autowired
    private TeachersRepository teachersRepository;
    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private TagsRepository tagsRepository;
    @Autowired
    private WorksRepository worksRepository;

    @PostMapping("/")
    public String addWork(@RequestBody WorkDto workDto) {
        Teacher teacher = teachersRepository.getOne(workDto.getTeacherId());
        Student student = studentsRepository.getOne(workDto.getStudentId());
        List<Tag> tags = tagsRepository.findAllByNameIn(workDto.getTags());
        Work work = Work.builder().description(workDto.getDescription()).title(workDto.getTitle()).student(student).teacher(teacher)
                .tags(tags).build();
        worksRepository.save(work);
        return "Work successfully exported";
    }
}
