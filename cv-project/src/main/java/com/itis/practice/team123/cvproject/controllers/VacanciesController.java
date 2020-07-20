package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.VacancyDto;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.VacanciesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class VacanciesController {
    private final VacanciesService vacanciesService;

    @GetMapping("/vacancies")
    @PreAuthorize("isAuthenticated()")
    public String getVacancies() {
        return "vacancies";
    }

    @GetMapping("/vacancy/{id}")
    @PreAuthorize("isAuthenticated()")
    public String getVacancy(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "vacancy";
    }

    @PostMapping("/vacancy/addVacancy")
    @PreAuthorize("hasAnyRole('COMPANY')")
    public ResponseEntity<?> addVacancy(@RequestBody VacancyDto vacancyDto,
                                        @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        return ResponseEntity.ok().build();
    }
}
