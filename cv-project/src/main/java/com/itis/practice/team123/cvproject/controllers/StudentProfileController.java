package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.services.interfaces.StudentsService;
import com.itis.practice.team123.cvproject.utils.Initializer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentProfileController {

    private final StudentsService studentsService;

    @PreAuthorize("permitAll()")
    @GetMapping(value = {"/api/students/{id}", "/students/{id}"})
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {
        return ResponseEntity.ok(Initializer.initializeAndUnproxy(studentsService.getStudentById(id)));
    }
}
