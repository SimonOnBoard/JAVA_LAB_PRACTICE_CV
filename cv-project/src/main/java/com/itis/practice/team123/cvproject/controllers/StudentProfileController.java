package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.services.interfaces.StudentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentProfileController {

    private final StudentsService studentsService;

    public StudentProfileController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/api/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentsService.getStudentById(id));
    }
}
