package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.*;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.StudentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/studentAccount/")
public class StudentAccountController {

    private final StudentsService studentsService;

    public StudentAccountController(StudentsService studentsService) {
        this.studentsService = studentsService;
    }

    @PutMapping("/info/update")
    public ResponseEntity<?> updateBaseInfo(@RequestBody StudentForm studentForm,
                                            @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        studentsService.updateStudentBaseInfo(studentForm, userDetails.getUserId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/competences/update")
    public ResponseEntity<?> updateCompetencesInfo(@RequestBody TagDto tagDto,
                                                   @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        studentsService.updateStudentCompetencesInfo(tagDto, userDetails.getUserId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/languages/update")
    public ResponseEntity<?> updateLanguagesInfo(@RequestBody LanguageDto languageDto,
                                                 @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        studentsService.updateStudentLanguagesInfo(languageDto, userDetails.getUserId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/education/update")
    public ResponseEntity<?> updateEducationInfo(@RequestBody EducationDto educationDto,
                                                 @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        studentsService.updateStudentEducationInfo(educationDto, userDetails.getUserId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/certificates/update")
    public ResponseEntity<?> updateCertificatesInfo(@RequestBody CertificateDto certificateDto,
                                                    @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        studentsService.updateStudentCertificatesInfo(certificateDto, userDetails.getUserId());
        return ResponseEntity.ok().build();
    }
}
