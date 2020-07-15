package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.StudentsService;
import com.itis.practice.team123.cvproject.utils.Initializer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentProfileController {

    private final StudentsService studentsService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = {"/api/students/{id}", "/students/{id}", "/api/students/profile"})
    public ResponseEntity<Student> getStudent(@PathVariable(name = "id", required = false) Long id,
                                              @AuthenticationPrincipal UserDetailsImpl<Student> studentUserDetails) {
        if(id == null) return ResponseEntity.ok(Initializer.initializeAndUnproxy(studentsService.getStudentById(studentUserDetails.getUserId())));
        return ResponseEntity.ok(Initializer.initializeAndUnproxy(studentsService.getStudentById(id)));
    }
}
