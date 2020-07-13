package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.WorkDto;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudiesIntegrationAPI {
    private TeachersRepository teachersRepository;
    private StudentsRepository studentsRepository;
    private TagsRepository tagsRepository;
    private WorksRepository worksRepository;

    public StudiesIntegrationAPI(TeachersRepository teachersRepository, StudentsRepository studentsRepository, TagsRepository tagsRepository, WorksRepository worksRepository) {
        this.teachersRepository = teachersRepository;
        this.studentsRepository = studentsRepository;
        this.tagsRepository = tagsRepository;
        this.worksRepository = worksRepository;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/works")
    public String addWork(@RequestBody WorkDto workDto) {
        Teacher teacher = teachersRepository.getOne(workDto.getTeacherId());
        Student student = studentsRepository.getOne(workDto.getStudentId());
        List<Tag> tags = tagsRepository.findAllByNameIn(workDto.getTags());
        Work work = Work.builder()
                .description(workDto.getDescription())
                .title(workDto.getTitle())
                .student(student)
                .teacher(teacher)
                .tags(tags).build();
        worksRepository.save(work);
        return "Work successfully exported";
    }
}
