package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.enums.LanguageLevel;
import com.itis.practice.team123.cvproject.enums.Role;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.AccessDeniedException;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = {"/profile/{id}", "/profile"})
    public String getTeacherProfile(Model model,
                                    @AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                    @PathVariable(name = "id", required = false) Long id) {
        // TODO: 12.07.2020 Подумать на тему конфиденциального отображения профилей
        try {
            model.addAttribute("isOwner", id == null || id.equals(userDetails.getUserId()));
            return id == null ? profileService.getProfile(userDetails.getUser(), model) : profileService.getProfile(id, model);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = {"/api/profile/{id}", "/api/profile"})
    public ResponseEntity<?> getApiForProfile(Model model,
                                              @AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                              @PathVariable(name = "id", required = false) Long id) {
        try {
            return ResponseEntity.ok()
                    .body(id == null ? profileService.getProfileForApi(userDetails.getUser()) : profileService.getProfileForApi(id));
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/editTeacherProfile/{id}")
    public String getEditTeacherProfilePage(Model model,
                                            @AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                            @PathVariable("id") Long id) throws AccessDeniedException {
        checkAuthorities(userDetails.getRole(), id, userDetails.getUserId());
        model.addAttribute("myEnum", LanguageLevel.values());
        profileService.getProfile(id, model);
        return "editTeacherProfilePage";
    }

    private void checkAuthorities(String role, Long id, Long userId) throws AccessDeniedException {
        if (!((Role.ADMIN.name().equals(role)) | id.equals(userId)))
            throw new AccessDeniedException("You can't do this");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/editCompanyProfile/{id}")
    public String getEditCompanyProfilePage(Model model,
                                            @AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                            @PathVariable("id") Long id) throws AccessDeniedException {
        checkAuthorities(userDetails.getRole(), id, userDetails.getUserId());
        profileService.getProfile(id, model);
        return "editCompanyProfilePage";
    }

}
