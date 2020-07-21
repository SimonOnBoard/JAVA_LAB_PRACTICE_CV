package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.ProjectDto;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentProjectsController {
    @PreAuthorize("hasAnyRole('TEACHER', 'COMPANY', 'ADMIN')")
    @GetMapping("students/{id}/projects")
    public String getProjectsPage(@PathVariable("id") Long userId,
                                                      @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        if (userDetails.getUserId().equals(userId)) {
            return "redirect:/studentAccount/projects";
        } else {
            return "studentProjects";
        }
    }

    @PreAuthorize("hasAnyRole('STUDENT','TEACHER','ADMIN','COMPANY')")
    @GetMapping("studentAccount/projects")
    public String getProjectsPage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        return "studentNewProject";
    }

    @PreAuthorize("hasAnyRole('STUDENT','TEACHER','ADMIN','COMPANY')")
    @GetMapping("/getProject/{id}")
    public String showProjectPage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                  @PathVariable Long id) {
        return "showProject";
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @PostMapping("/projects/newProject")
    // TODO: 20.07.2020 возвращать dto шку из сервиса
    public ResponseEntity<?> addNewProject(ProjectDto projectDto,
                                           @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        //projectsService.addNewProject(projectDto, userDetails.getUser());
        return ResponseEntity.ok().build();
    }

}
