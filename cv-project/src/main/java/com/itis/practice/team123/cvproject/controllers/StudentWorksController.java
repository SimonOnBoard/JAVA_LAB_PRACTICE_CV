package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.WeightedWorkDto;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class StudentWorksController {
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/works/{id}")
    public String getStudentsWorksPage() {
        return "studentWorks";
    }
}
