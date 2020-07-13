package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.enums.LanguageLevel;
import com.itis.practice.team123.cvproject.enums.Role;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.ProfileService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.AccessDeniedException;

@Controller
public class ProfileController {
    private ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
    //Сделать Rest подобным
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = {"/profile/{id}", "/profile"})
    public String getTeacherProfile(Model model,
                                    @AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                    @PathVariable(name = "id", required = false) Long id) throws AccessDeniedException {
        // TODO: 12.07.2020 Подумать на тему конфиденциального отображения профилей
        try {
            model.addAttribute("isOwner", id == null || id.equals(userDetails.getUserId()));
            return id == null ? profileService.getProfile(userDetails.getUser(), model) : profileService.getProfile(id, model);
        } catch (AccessDeniedException e) {
            throw new AccessDeniedException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    //возвращает страничку для редактирования профиля проверя пользователя
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/editTeacherProfile/{id}")
    public String getEditTeacherProfilePage(Model model,
                                            @AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                            @PathVariable("id") Long id) throws AccessDeniedException {
        if (Role.ADMIN.name().equals(userDetails.getRole()) | id.equals(userDetails.getUserId())) {
            model.addAttribute("myEnum", LanguageLevel.values());
            profileService.getProfile(id, model);
        } else {
            throw new AccessDeniedException("You can't do this");
        }
        return "editTeacherProfilePage";
    }
}
