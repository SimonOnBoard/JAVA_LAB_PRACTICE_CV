package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.UserDto;
import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.repositories.CompanyRepository;
import com.itis.practice.team123.cvproject.repositories.StudentsRepository;
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
    private final StudentsRepository studentsRepository;
    @Override
    public UserDto registerUser(UserForm userForm) {
        User userToReturn = null;
        switch (userForm.getRole()){
            case ADMIN:
                 userToReturn = User.from(userForm);
                 userToReturn.setPassword(passwordEncoder.encode(userToReturn.getPassword()));
                 usersRepository.save(userToReturn);
                break;
            case TEACHER:
                Teacher teacher = Teacher.fromUserForm(userForm);
                teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
                teachersRepository.save(teacher);
                userToReturn = teacher;
                break;
            case COMPANY:
                Company company = Company.fromUserForm(userForm);
                company.setPassword(passwordEncoder.encode(company.getPassword()));
                companyRepository.save(company);
                userToReturn = company;
                break;
            case STUDENT:
                Student student = Student.fromUserForm(userForm);
                student.setPassword(passwordEncoder.encode(student.getPassword()));
                studentsRepository.save(student);
                userToReturn = student;
                break;
            default:
                throw new IllegalArgumentException("Unexpected value: " + userForm.getRole());
        }
        return UserDto.from(userToReturn);
    }
}
