package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.ProjectCommentDto;
import com.itis.practice.team123.cvproject.dto.ProjectDto;
import com.itis.practice.team123.cvproject.models.Project;
import com.itis.practice.team123.cvproject.models.User;

import java.util.List;

public interface ProjectsService {
    List<Project> getAllProjects(Long userId);
    Project getProjectById(Long id);

    ProjectDto addNewProject(ProjectDto projectDto, User user);
    void commentProject(ProjectCommentDto projectCommentDto, Long projectId, User user);
}
