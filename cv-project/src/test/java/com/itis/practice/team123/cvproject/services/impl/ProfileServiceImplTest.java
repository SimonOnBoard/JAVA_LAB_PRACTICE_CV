package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.ProjectDto;
import com.itis.practice.team123.cvproject.enums.Role;
import com.itis.practice.team123.cvproject.models.Project;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.services.interfaces.ProfileService;
import com.itis.practice.team123.cvproject.services.interfaces.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProfileServiceImplTest {
    @Mock
    private UsersService usersService;
    @Autowired
    private ProfileService profileService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getProfile_Roles_Check() {
        User userAdmin = new User();
        userAdmin.setRole(Role.ADMIN);
        userAdmin.setId(1L);
        User userTeacher = new User();
        userTeacher.setRole(Role.TEACHER);
        userTeacher.setId(2L);
        User userStudent = new User();
        userStudent.setRole(Role.STUDENT);
        userStudent.setId(3L);
        User userCompany = new User();
        userCompany.setRole(Role.COMPANY);
        userCompany.setId(4L);

        when(usersService.getUser(1L)).thenReturn(userAdmin);
        when(usersService.getUser(2L)).thenReturn(userTeacher);
        when(usersService.getUser(3L)).thenReturn(userStudent);
        when(usersService.getUser(4L)).thenReturn(userCompany);

        profileService.getProfile(userAdmin, new ConcurrentModel());
        profileService.getProfile(userTeacher, new ConcurrentModel());
        profileService.getProfile(userStudent, new ConcurrentModel());
        profileService.getProfile(userCompany, new ConcurrentModel());

        assertDoesNotThrow((ThrowingSupplier<IllegalArgumentException>) IllegalArgumentException::new);
    }

    @Test
    void testGetProfile() {
        when(usersService.getUser(1L)).thenReturn(new User());
        profileService.getProfile(1L, new ConcurrentModel());
        assertDoesNotThrow((ThrowingSupplier<Exception>) Exception::new);
    }

    @Test
    void getProfileForApi() {
        Teacher teacher = new Teacher();
        teacher.setRole(Role.TEACHER);
        profileService.getProfileForApi(teacher);
        assertDoesNotThrow((ThrowingSupplier<Exception>) Exception::new);
    }

    @Test
    void testGetProfileForApi() {
        when(usersService.getUser(1L)).thenReturn(new User());
        profileService.getProfileForApi(1L);
        assertDoesNotThrow((ThrowingSupplier<Exception>) Exception::new);
    }
}