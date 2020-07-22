package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.models.Competence;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.repositories.CompetenceRepository;
import com.itis.practice.team123.cvproject.services.interfaces.CompetenceConfirmingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompetenceConfirmingServiceImpl implements CompetenceConfirmingService {

    private final CompetenceRepository competenceRepository;

    public CompetenceConfirmingServiceImpl(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }

    @Override
    @Transactional
    public void confirmCompetenceFromStudentProfile(Long competenceId, User user) {
        Teacher teacher = (Teacher) user;
        Competence competence = competenceRepository.findById(competenceId).orElseThrow(() -> new IllegalArgumentException("Competence with id " + competenceId + " doesn't exist"));
        competence.setConfirmed(true);
        competence.getConfirmedTeachers().add(teacher);
    }
}
