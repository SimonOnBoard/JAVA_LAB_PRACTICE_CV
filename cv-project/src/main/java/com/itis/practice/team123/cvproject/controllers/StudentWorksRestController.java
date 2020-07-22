package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.WeightedWorkDto;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.repositories.WorksRepository;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.WeightsAssigner;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentWorksRestController {
    private final WorksRepository worksRepository;
    private final WeightsAssigner weightsAssigner;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/load/works/{id}")
    public List<WeightedWorkDto> getStudentsWorks(@PathVariable Long id) {
        List<Work> works = worksRepository.getWorksByStudentId(id);
        return weightsAssigner.assignWorkWeights(works);
    }
}
