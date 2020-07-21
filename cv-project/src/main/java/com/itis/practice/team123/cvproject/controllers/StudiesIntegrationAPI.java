package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.BearerDto;
import com.itis.practice.team123.cvproject.dto.TeachersWorkDto;
import com.itis.practice.team123.cvproject.dto.WorkDto;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.repositories.*;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.impl.WorksServiceImpl;
import com.itis.practice.team123.cvproject.services.interfaces.WorksService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudiesIntegrationAPI {
    @Value("${bearer.token}")
    private String bearerToken;
    private final WorksService worksService;


    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/addWorkByTeacher")
    // TODO: 20.07.2020 убрать составление dto напрямую в сервис во вспомогательный метод перед вызовом addWord
    public ResponseEntity<?> addWorkByTeacher(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                              @RequestBody TeachersWorkDto teachersWorkDto) {
        WorkDto workDto = WorkDto.builder()
                .teacherId(userDetails.getUserId())
                .studentId(teachersWorkDto.getStudentId())
                .tags(teachersWorkDto.getTags())
                .title(teachersWorkDto.getTitle())
                .description(teachersWorkDto.getDescription())
                .links(teachersWorkDto.getLinks())
                .build();
        try {
            worksService.addWork(workDto);
            return ResponseEntity.ok("Work successfully exported");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/addWork")
    public ResponseEntity<?> addWork(@RequestBody BearerDto bearerDto) {
        if (!bearerDto.getBearer().equals(bearerToken)) {
            return ResponseEntity.status(403).build();
        }
        try {
            worksService.addWork(bearerDto.getWorkDto());
            return ResponseEntity.ok("Work successfully exported");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
