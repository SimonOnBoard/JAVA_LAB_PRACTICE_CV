package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Teacher;

import java.util.List;

public interface AdminUsersListService {
    List<Student> getAllStudents();
    List<Teacher> getAllTeachers();
    List<Company> getAllCompanies();
}
