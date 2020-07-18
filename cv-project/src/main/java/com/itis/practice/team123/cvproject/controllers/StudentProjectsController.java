package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.ProjectDto;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping("studentAccount/projects")
    public String getProjectsPage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        return "studentNewProject";
    }
}
