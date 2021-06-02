package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.services.interfaces.AdminService;
import com.itis.practice.team123.cvproject.services.interfaces.AdminUsersListService;
import com.itis.practice.team123.cvproject.services.interfaces.CompanyService;
import com.itis.practice.team123.cvproject.services.interfaces.TeachersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final AdminUsersListService adminUsersListService;
    private final CompanyService companyService;
    private final TeachersService teachersService;
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/panel")
    public String getAdminPanel() {
        return "panel";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/addUser")
    public String getAddUserPage() {
        return "addUserPage";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(UserForm userForm) {
        return ResponseEntity.ok().body(adminService.registerUser(userForm));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = {"api/allStudents","/allStudents"})
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok().body(adminUsersListService.getAllStudents());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getStudentsPage")
    public String getStudentsPage() {
        return "students";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getCompaniesPage")
    public String getCompaniesPage() {
        return "companies";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getTeachersPage")
    public String getTeachersPage() {
        return "teachers";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = {"api/allTeachers","/allTeachers"})
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok().body(adminUsersListService.getAllTeachers());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = {"api/allCompanies","/allCompanies"})
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok().body(adminUsersListService.getAllCompanies());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getCause());
    }
}
