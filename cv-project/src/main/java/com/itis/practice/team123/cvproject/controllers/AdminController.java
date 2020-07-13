package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/panel")
    public String getAdminPanel(@AuthenticationPrincipal UserDetailsImpl<User> userDetails) {
        return "panel";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/addUser")
    public String getAddUserPage(@AuthenticationPrincipal UserDetailsImpl<User> userUserDetails) {
        return "addUserPage";
    }

    //Придумать метод для возвращения всех возможных api  admin + переделать метод добавления пользователя в rest
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addUser")
    public String addUser(@AuthenticationPrincipal UserDetailsImpl<User> userUserDetails,
                          UserForm userForm,
                          Model model) {
        try {
            adminService.registerUser(userForm);
        } catch (IllegalArgumentException e) {
            model.addAttribute("info", e.getMessage());
            return "addUserPage";
        }
        return "panel";
    }
}
