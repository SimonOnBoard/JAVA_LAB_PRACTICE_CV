package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.User;

public interface CompetenceConfirmingService {
    void confirmCompetenceFromStudentProfile(Long competenceId, User user);
    // TODO добавить подтверждение через ЛК препода
}
