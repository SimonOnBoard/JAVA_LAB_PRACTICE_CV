package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.WeightedWorkDto;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.repositories.WorksRepository;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.WeightsAssigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class StudentWorksController {
    private WorksRepository worksRepository;
    private WeightsAssigner weightsAssigner;

    public StudentWorksController(WorksRepository worksRepository, WeightsAssigner weightsAssigner) {
        this.worksRepository = worksRepository;
        this.weightsAssigner = weightsAssigner;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/works/{id}")
    public List<WeightedWorkDto> getStudentsWorks(@PathVariable Long id) {
        List<Work> works = worksRepository.getWorksByStudentId(id);
        return weightsAssigner.assignWorkWeights(works);
    }
}
