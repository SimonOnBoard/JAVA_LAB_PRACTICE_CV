package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.repositories.CompanyRepository;
import com.itis.practice.team123.cvproject.repositories.TeachersRepository;
import com.itis.practice.team123.cvproject.repositories.UsersRepository;
import com.itis.practice.team123.cvproject.services.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.itis.practice.team123.cvproject.enums.Role;

import static com.itis.practice.team123.cvproject.enums.Role.ADMIN;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UsersRepository usersRepository;
    private final TeachersRepository teachersRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyRepository companyRepository;

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
            case COMPANY:
                Company company = Company.fromUserForm(userForm);
                company.setPassword(passwordEncoder.encode(company.getPassword()));
                companyRepository.save(company);
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + userForm.getRole());
        }
    }
}
