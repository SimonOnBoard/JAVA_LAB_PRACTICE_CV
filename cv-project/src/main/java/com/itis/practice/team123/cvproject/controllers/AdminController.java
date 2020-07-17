package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.AdminService;
import io.swagger.models.Response;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/panel")
    public String getAdminPanel() {
        return "panel";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/addUser")
    public String getAddUserPage() {
        return "addUserPage";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody UserForm userForm) {
        try {
            return ResponseEntity.ok().body(adminService.registerUser(userForm));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getCause());
        }
    }
}
