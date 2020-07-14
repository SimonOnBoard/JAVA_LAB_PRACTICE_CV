package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.CompanyEditForm;
import com.itis.practice.team123.cvproject.dto.TeacherEditForm;
import com.itis.practice.team123.cvproject.enums.Role;
import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Post;
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
public class EditContentController {
    private final TeachersService teachersService;
    private final CompanyService companyService;
    private final String okAnswer;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/editTeacherProfile/{id}")
    public ResponseEntity<?> editTeacherProfilePage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                         @PathVariable("id") Long id,
                                         TeacherEditForm teacherEditForm) throws AccessDeniedException {
        checkAuthorities(userDetails.getRole(), id, userDetails.getUserId());
        teachersService.updateTeacher(teacherEditForm, id);
        return ResponseEntity.ok(okAnswer);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/editCompanyProfile/{id}")
    public ResponseEntity<?> editCompanyProfilePage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                                    @PathVariable("id") Long id,
                                                    CompanyEditForm companyEditForm) throws AccessDeniedException {
        checkAuthorities(userDetails.getRole(), id, userDetails.getUserId());
        companyService.updateCompany(companyEditForm, id);
        return ResponseEntity.ok(okAnswer);
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addLanguage/{id}")
    public ResponseEntity<?> addLanguage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                       @PathVariable("id") Long id,
                       Language language) throws AccessDeniedException {
        checkAuthorities(userDetails.getRole(), id, userDetails.getUserId());
        teachersService.addLanguage(id, language);
        return ResponseEntity.ok().body(okAnswer);
    }

    @PreAuthorize("hasRole('COMPANY')")
    @PostMapping("/addPost")
    public ResponseEntity<?> addLanguage(@AuthenticationPrincipal UserDetailsImpl<Company> userDetails,
                                         Post post){
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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/removeLanguage/{id}/{langId}")
    public ResponseEntity<?> deleteLanguage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                            @PathVariable("id") Long id,
                                            @PathVariable("langId") Long langId) throws AccessDeniedException {
        checkAuthorities(userDetails.getRole(), id, userDetails.getUserId());
        try {
            teachersService.removeLanguage(id, langId);
            return ResponseEntity.ok(okAnswer);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    private void checkAuthorities(String role,
                                  Long id,
                                  Long userId) throws AccessDeniedException {
        if (!((Role.ADMIN.name().equals(role)) | id.equals(userId))) throw new AccessDeniedException("You can't do this");
    }
}
