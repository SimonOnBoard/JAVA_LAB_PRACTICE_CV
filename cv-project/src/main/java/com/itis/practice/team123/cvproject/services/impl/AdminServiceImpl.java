package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.repositories.TeachersRepository;
import com.itis.practice.team123.cvproject.repositories.UsersRepository;
import com.itis.practice.team123.cvproject.services.interfaces.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.itis.practice.team123.cvproject.enums.Role;

import static com.itis.practice.team123.cvproject.enums.Role.ADMIN;

@Service
public class AdminServiceImpl implements AdminService {

    private UsersRepository usersRepository;
    private TeachersRepository teachersRepository;
    private PasswordEncoder passwordEncoder;

    public AdminServiceImpl(UsersRepository usersRepository, TeachersRepository teachersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.teachersRepository = teachersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void registerUser(UserForm userForm) {
        switch (userForm.getRole()){
            case ADMIN:
                 User user = User.from(userForm);
                 user.setPassword(passwordEncoder.encode(user.getPassword()));
                 usersRepository.save(user);
                break;
            case TEACHER:
                Teacher teacher = Teacher.fromUserForm(userForm);
                teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
                teachersRepository.save(teacher);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + userForm.getRole());
        }
    }
}
