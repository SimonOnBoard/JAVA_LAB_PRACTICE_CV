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
        return ResponseEntity.ok().body(teachersService.updateTeacher(teacherEditForm, (Teacher) userDetails.getUser()));
    }

    @PreAuthorize("hasRole('COMPANY')")
    @PostMapping(value = {"/editCompanyProfile", "/api/editCompanyChanges"})
    public ResponseEntity<?> editCompanyProfilePage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                                    CompanyEditForm companyEditForm) {
        return ResponseEntity.ok(companyService.updateCompany(companyEditForm, (Company) userDetails.getUser()));
    }


    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/addLanguage")
    public ResponseEntity<?> addLanguage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                         Language language) {
        return ResponseEntity.ok().body(teachersService.addLanguage(userDetails.getUserId(), language));
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("/removeLanguage/{langId}")
    public ResponseEntity<?> deleteLanguage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                            @PathVariable("langId") Long langId) {
        return ResponseEntity.ok().body(teachersService.removeLanguage((Teacher) userDetails.getUser(), langId));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException exception) {
        return ResponseEntity.ok().body(exception.getMessage());
    }

    @PreAuthorize("hasRole('COMPANY')")
    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@AuthenticationPrincipal UserDetailsImpl<Company> userDetails,
                                     Post post) {
        return ResponseEntity.ok().body(companyService.addPost(userDetails.getUser(), post));
    }

    @PreAuthorize("hasRole('COMPANY')")
    @PostMapping("/removePost/{id}")
    public ResponseEntity<?> removePost(@AuthenticationPrincipal UserDetailsImpl<Company> userDetails,
                                        @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(companyService.removePost(userDetails.getUser(), id));
    }
}
