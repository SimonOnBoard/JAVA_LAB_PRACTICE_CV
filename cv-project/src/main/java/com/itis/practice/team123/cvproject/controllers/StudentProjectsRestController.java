package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.ProjectCommentDto;
import com.itis.practice.team123.cvproject.dto.ProjectDto;
import com.itis.practice.team123.cvproject.models.Project;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.ProjectsService;
import com.itis.practice.team123.cvproject.utils.Initializer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//вообще меппинги кривые, к диному стилю привести бы
public class StudentProjectsRestController {

    private final ProjectsService projectsService;

    // TODO: 20.07.2020 обработать illegal argument при выпуске  в продакшн
    @PreAuthorize("hasAnyRole('TEACHER', 'COMPANY', 'ADMIN')")
    @GetMapping(value = {"/api/students/{id}/projects", "/load/students/{id}/projects"})
    public ResponseEntity<List<ProjectDto>> getStudentProjects(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(ProjectDto.from(projectsService.getAllProjects(id)));
    }

    // TODO: 20.07.2020 обработать illegal argument при выпуске  в продакшн
    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'COMPANY', 'ADMIN')")
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok().body(Initializer.initializeAndUnproxy(projectsService.getProjectById(projectId)));
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping(value = {"/api/studentAccount/projects", "/load/studentAccount/projects"})
    public ResponseEntity<List<ProjectDto>> getProjects(@AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        return ResponseEntity.ok().body(ProjectDto.from(projectsService.getAllProjects(userDetails.getUserId())));
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping("/projects/{projectId}/newComment")
    // TODO: 20.07.2020 возвращать dto шку из сервиса
    public ResponseEntity<?> commentProject(@RequestBody ProjectCommentDto projectCommentDto,
                                            @PathVariable("projectId") Long projectId,
                                            @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        projectsService.commentProject(projectCommentDto, projectId, userDetails.getUser());
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @PostMapping("/projects/newProject")
    // TODO: 20.07.2020 возвращать dto шку из сервиса
    public ResponseEntity<?> addNewProject(ProjectDto projectDto,
                                           @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        return ResponseEntity.ok().body(projectsService.addNewProject(projectDto, userDetails.getUser()));
    }
}
