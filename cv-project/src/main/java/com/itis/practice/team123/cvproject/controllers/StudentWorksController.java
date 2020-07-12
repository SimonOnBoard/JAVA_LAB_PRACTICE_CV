package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.repositories.WorksRepository;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StudentWorksController {
    @Autowired
    private WorksRepository worksRepository;

    @GetMapping("/works/{id}")
    public ModelAndView getStudentsWorks(@PathVariable Long id) {
        List<Work> works = worksRepository.getWorksByStudentId(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("works");
        modelAndView.addObject("works", works);
        return modelAndView;
    }
}
