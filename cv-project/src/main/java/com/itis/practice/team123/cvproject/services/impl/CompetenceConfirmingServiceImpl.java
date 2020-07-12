package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.models.Competence;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.repositories.CompetenceRepository;
import com.itis.practice.team123.cvproject.services.interfaces.CompetenceConfirmingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceConfirmingServiceImpl implements CompetenceConfirmingService {

    private final CompetenceRepository competenceRepository;

    public CompetenceConfirmingServiceImpl(CompetenceRepository competenceRepository) {
        this.competenceRepository = competenceRepository;
    }

    //обработать исключение когда это делает не учитель, выполнить проверку
    @Override
    public void confirmCompetenceFromStudentProfile(Long competenceId, User user) {
        Teacher teacher = (Teacher) user;

        //можно завести отдельный сервис для компетенций и доставать оттуда компетенцию
        //почему не find?
        Competence competence = competenceRepository.getOne(competenceId);
        competence.setConfirmed(true);

        competence.getConfirmedTeachers().add(teacher);
        competenceRepository.save(competence);
    }
}
