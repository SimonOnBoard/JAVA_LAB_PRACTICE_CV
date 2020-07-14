package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.ProjectCommentDto;
import com.itis.practice.team123.cvproject.models.*;
import com.itis.practice.team123.cvproject.repositories.ProjectCommentsRepository;
import com.itis.practice.team123.cvproject.repositories.ProjectsRepository;
import com.itis.practice.team123.cvproject.repositories.StudentsRepository;
import com.itis.practice.team123.cvproject.services.interfaces.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectsServiceImpl implements ProjectsService {

    private final StudentsRepository studentsRepository;
    private final ProjectsRepository projectsRepository;
    private final ProjectCommentsRepository projectCommentsRepository;

    @Override
    public List<Project> getAllProjects(Long userId) {
        Student student = studentsRepository.getOne(userId);
        return projectsRepository.findAllByOwner(student);
    }

    @Override
    public Project getProjectById(Long id) {
        return projectsRepository.getOne(id);
    }

    @Override
    public void commentProject(ProjectCommentDto projectCommentDto, Long projectId, User user) {
        Teacher teacher = (Teacher) user;
        Project project = getProjectById(projectId);

        projectCommentsRepository.save(ProjectComment.builder()
                .comment(projectCommentDto.getComment())
                .dateTime(LocalDateTime.now())
                .project(project)
                .teacher(teacher)
                .build());
    }
}
