package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.VacancyDto;
import com.itis.practice.team123.cvproject.services.interfaces.VacanciesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class VacanciesController {
    private VacanciesService vacanciesService;

    @GetMapping("/vacancies/{id}")
    @PreAuthorize("isAuthenticated()")
    public String getVacancy(@PathVariable("id") Long id, Model model) {
        model.addAttribute("vacancy", vacanciesService.getVacancy(id));
        return "companyVacancy";
    }
}
