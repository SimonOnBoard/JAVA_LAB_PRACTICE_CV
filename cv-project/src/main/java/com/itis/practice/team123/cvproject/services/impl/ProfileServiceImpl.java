package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.enums.Role;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.services.interfaces.ProfileService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.nio.file.AccessDeniedException;

import static com.itis.practice.team123.cvproject.enums.Role.*;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Override
    public Role getProfile(User user, Model model) throws IllegalStateException {
        switch (user.getRole()) {
            case TEACHER:
                loadTeacherInfo(user, model);
            case COMPANY:
                loadCompanyInfo(user, model);
                break;
            case ADMIN:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + user.getClass());
        }
        return user.getRole();
    }

    private void loadCompanyInfo(User user, Model model) {

    }

    private void loadTeacherInfo(User user, Model model) {
        Teacher teacher = (Teacher) user;
        if (teacher.getName() != null) model.addAttribute("name", teacher.getName());
        if (teacher.getPatronymic() != null) model.addAttribute("patronymic", teacher.getPatronymic());
        if (teacher.getSurname() != null) model.addAttribute("surname", teacher.getSurname());
        if (teacher.getInstitution() != null) model.addAttribute("institution", teacher.getInstitution());
        if (teacher.getLanguages() != null) model.addAttribute("languages", teacher.getLanguages());
        if (teacher.getAdditionalInfo() != null) model.addAttribute("info", teacher.getAdditionalInfo());
        model.addAttribute("id", teacher.getId());
    }

    @Override
    public Role getProfile(Long id, Model model) throws AccessDeniedException {
        return null;
    }
}
