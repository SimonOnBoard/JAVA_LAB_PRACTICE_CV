package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.TagService;
import com.itis.practice.team123.cvproject.services.interfaces.VacanciesService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.security.PermitAll;

@Controller
@AllArgsConstructor
public class VacanciesController {
    private final VacanciesService vacanciesService;
    private final TagService tagService;

    @GetMapping("/vacancies")
    @PreAuthorize("permitAll()")
    public String getVacancies(Model model) {
        model.addAttribute("vacancies", vacanciesService.getAllVacancies());
        return "companyVacancies";
    }

    @GetMapping("/vacancies/{id}")
    @PreAuthorize("isAuthenticated()")
    public String getVacancy(@PathVariable("id") Long id, Model model) {
        model.addAttribute("vacancy", vacanciesService.getVacancy(id));
        return "companyVacancy";
    }

    @GetMapping("/vacancies/newVacancy")
    @PreAuthorize("hasRole('COMPANY')")
    public String addVacancyPage(Model model, @AuthenticationPrincipal UserDetailsImpl<?> userDetails) {
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("vacancies", vacanciesService.getAllVacanciesByCompanyId(userDetails.getUserId()));
        return "companyNewVacancy";
    }
}
