package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @PreAuthorize("permitAll()")
    @GetMapping("/login")
    public String startLoginProcess(@RequestParam(value = "error", required = false) Boolean info, @AuthenticationPrincipal UserDetailsImpl<?> userDetails, Model model) {
        if (userDetails != null) return "redirect:/profile";
        if (info != null && info)
            model.addAttribute("info", "Something went wrong) Check Login or Password or Register user");
        return "login";
    }
}