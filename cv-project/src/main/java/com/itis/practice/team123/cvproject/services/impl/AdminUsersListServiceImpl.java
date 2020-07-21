package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.repositories.CompanyRepository;
import com.itis.practice.team123.cvproject.repositories.StudentsRepository;
import com.itis.practice.team123.cvproject.repositories.TeachersRepository;
import com.itis.practice.team123.cvproject.services.interfaces.AdminUsersListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUsersListServiceImpl implements AdminUsersListService {

    private final StudentsRepository studentsRepository;
    private final TeachersRepository teachersRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentsRepository.findAll();
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teachersRepository.findAll();
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
