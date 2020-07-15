package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.CompanyEditForm;
import com.itis.practice.team123.cvproject.dto.TeacherEditForm;
import com.itis.practice.team123.cvproject.enums.Role;
import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Post;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.CompanyService;
import com.itis.practice.team123.cvproject.services.interfaces.TeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@Controller
@RequiredArgsConstructor
public class TeacherCompanyContentController {
    private final TeachersService teachersService;
    private final CompanyService companyService;
    private final String okAnswer;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping(value = {"/editTeacherProfile", "/api/editTeacherProfile"})
    public ResponseEntity<?> editTeacherProfilePage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                                    TeacherEditForm teacherEditForm) {
        teachersService.updateTeacher(teacherEditForm, (Teacher) userDetails.getUser());
        return ResponseEntity.ok(okAnswer);
    }

    @PreAuthorize("hasRole('COMPANY')")
    @PostMapping(value = {"/editCompanyProfile", "/api/editCompanyChanges"})
    public ResponseEntity<?> editCompanyProfilePage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                                    CompanyEditForm companyEditForm) {
        companyService.updateCompany(companyEditForm, (Company) userDetails.getUser());
        return ResponseEntity.ok(okAnswer);
    }


    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/addLanguage")
    public ResponseEntity<?> addLanguage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                         Language language) {
        teachersService.addLanguage((Teacher) userDetails.getUser(), language);
        return ResponseEntity.ok().body(okAnswer);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/removeLanguage/{langId}")
    public ResponseEntity<?> deleteLanguage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                            @PathVariable("langId") Long langId) {
        try {
            teachersService.removeLanguage((Teacher) userDetails.getUser(), langId);
            return ResponseEntity.ok(okAnswer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasRole('COMPANY')")
    @PostMapping("/addPost")
    public ResponseEntity<?> addLanguage(@AuthenticationPrincipal UserDetailsImpl<Company> userDetails,
                                         Post post) {
        companyService.addPost(userDetails.getUser(), post);
        return ResponseEntity.ok().body(okAnswer);
    }

    @PreAuthorize("hasRole('COMPANY')")
    @PostMapping("/removePost/{id}")
    public ResponseEntity<?> addLanguage(@AuthenticationPrincipal UserDetailsImpl<Company> userDetails,
                                         @PathVariable("id") Long id) {
        companyService.removePost(userDetails.getUser(), id);
        return ResponseEntity.ok().body(okAnswer);
    }
}
