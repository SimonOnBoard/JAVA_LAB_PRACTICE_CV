package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileManageController {
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/test")
    public void test(Model model, @AuthenticationPrincipal UserDetailsImpl<?> userDetails){
        System.out.println(userDetails.getUserId());
        System.out.println(userDetails.getUser().getClass());
        System.out.println(userDetails.getUsername());

    }
}
