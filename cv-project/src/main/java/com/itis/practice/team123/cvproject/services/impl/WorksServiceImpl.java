package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.WorkDto;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.repositories.StudentsRepository;
import com.itis.practice.team123.cvproject.repositories.TagsRepository;
import com.itis.practice.team123.cvproject.repositories.TeachersRepository;
import com.itis.practice.team123.cvproject.repositories.WorksRepository;
import com.itis.practice.team123.cvproject.services.interfaces.WorksService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorksServiceImpl implements WorksService {
    private final TeachersRepository teachersRepository;
    private final StudentsRepository studentsRepository;
    private final TagsRepository tagsRepository;
    private final WorksRepository worksRepository;

    @Override
    public void addWork(WorkDto workDto) throws RuntimeException {
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
    }
}
