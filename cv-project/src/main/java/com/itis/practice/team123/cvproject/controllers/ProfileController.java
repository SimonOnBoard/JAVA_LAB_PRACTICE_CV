package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.ProfileService;
import org.dom4j.rule.Mode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.AccessDeniedException;

@Controller
public class ProfileController {
    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = {"/profile/{id}","/profile"})
    public String getTeacherProfile(Model model, @AuthenticationPrincipal UserDetailsImpl<?> userDetails, @PathVariable(name = "id", required = false) Long id) throws AccessDeniedException {
        try {
            model.addAttribute("isOwner", id == null || id.equals(userDetails.getUserId()));
            switch (id == null ? profileService.getProfile(userDetails.getUser(), model) : profileService.getProfile(id, model)) {
                case ADMIN:
                    return "panel";
                case TEACHER:
                    return "teacherProfile";
                case COMPANY:
                    break;
            }
        } catch (AccessDeniedException e) {
            //пока не придумать
            throw new AccessDeniedException(e.getMessage());
        }
        return null;
    }

}
