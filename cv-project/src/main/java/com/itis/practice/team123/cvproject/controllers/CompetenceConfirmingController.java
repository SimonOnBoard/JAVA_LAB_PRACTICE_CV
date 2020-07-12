package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.CompetenceConfirmingService;
import com.itis.practice.team123.cvproject.utils.RedirectUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompetenceConfirmingController {

    private final CompetenceConfirmingService competenceConfirmingService;

    public CompetenceConfirmingController(CompetenceConfirmingService competenceConfirmingService) {
        this.competenceConfirmingService = competenceConfirmingService;
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("{studentId}/confirm/{competenceId}")
    public ResponseEntity confirmCompetenceFromStudentProfile(@AuthenticationPrincipal UserDetailsImpl<?> userDetails,
                                                              @PathVariable("studentId") Long studentId,
                                                              @PathVariable("competenceId") Long competenceId) {
        competenceConfirmingService.confirmCompetenceFromStudentProfile(competenceId, userDetails.getUser());
        return new ResponseEntity<>(RedirectUtil.getHttpHeaders("/students/" + studentId),
                RedirectUtil.getHttpStatus());
    }

}
