package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.AdminService;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@AuthenticationPrincipal UserDetailsImpl<User> userUserDetails,
                            UserForm userForm) {
        try {
            adminService.registerUser(userForm);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getCause());
        }
        return ResponseEntity.ok().body("All is ok");
    }
}
