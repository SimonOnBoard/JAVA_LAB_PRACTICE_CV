package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.TeacherProfileInfo;
import com.itis.practice.team123.cvproject.enums.Role;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.TeachersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
@Controller
public class ProfileEditController {
    private TeachersService teachersService;

    public ProfileEditController(TeachersService teachersService) {
        this.teachersService = teachersService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/editTeacherProfile/{id}")
    public String getEditTeacherProfilePage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                            @PathVariable("id") Long id,
                                            TeacherProfileInfo teacherProfileInfo) throws AccessDeniedException {
        if (Role.ADMIN.name().equals(userDetails.getRole()) | id.equals(userDetails.getUserId())) {
            teachersService.updateTeacher(teacherProfileInfo, id);
        } else {
            throw new AccessDeniedException("You can't do this");
        }
        return "redirect:/profile/" + id;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/addLanguage/{id}")
    public @ResponseBody String addLanguage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails, @PathVariable("id") Long id, Language language) throws AccessDeniedException {
        if (Role.ADMIN.name().equals(userDetails.getRole()) | id.equals(userDetails.getUserId())) {
            teachersService.addLanguage(id, language);
        } else {
            throw new AccessDeniedException("You can't do this");
        }
        return "All is ok just check your profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/removeLanguage/{id}/{langId}")
    public String deleteLanguage(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                 @PathVariable("id") Long id,
                                 @PathVariable("langId") Long langId) throws AccessDeniedException {
        if (Role.ADMIN.name().equals(userDetails.getRole()) | id.equals(userDetails.getUserId())) {
            teachersService.removeLanguage(id, langId);
        } else {
            throw new AccessDeniedException("You can't do this");
        }
        return "redirect:/profile/" + id;
    }
}
