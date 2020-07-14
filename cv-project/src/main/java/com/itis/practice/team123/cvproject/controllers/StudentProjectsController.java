package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.ProjectCommentDto;
import com.itis.practice.team123.cvproject.dto.ProjectDto;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.ProjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentProjectsController {

    private final ProjectsService projectsService;

    @PreAuthorize("hasAnyRole('TEACHER', 'COMPANY', 'ADMIN')")
    @GetMapping("/api/students/{id}/projects")
    public ResponseEntity<List<ProjectDto>> getStudentProjects(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(ProjectDto.from(projectsService.getAllProjects(id)));
    }

    @PreAuthorize("hasAnyRole('STUDENT', 'TEACHER', 'COMPANY', 'ADMIN')")
    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ProjectDto> getProject(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok().body(ProjectDto.from(projectsService.getProjectById(projectId)));
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping("/api/studentAccount/projects")
    public ResponseEntity<List<ProjectDto>> getProjects(@AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        return ResponseEntity.ok().body(ProjectDto.from(projectsService.getAllProjects(userDetails.getUserId())));
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping("/projects/{projectId}/newComment")
    public ResponseEntity<?> commentProject(@RequestBody ProjectCommentDto projectCommentDto,
                                            @PathVariable("projectId") Long projectId,
                                            @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        projectsService.commentProject(projectCommentDto, projectId, userDetails.getUser() );
        return ResponseEntity.ok().build();
    }
}
