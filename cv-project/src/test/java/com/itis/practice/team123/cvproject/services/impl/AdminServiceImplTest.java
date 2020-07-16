package com.itis.practice.team123.cvproject.services.impl;

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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Table;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
class AdminServiceImplTest {
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private TeachersRepository teachersRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private StudentsRepository studentsRepository;

    private AdminService adminService;

    public AdminServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        this.adminService = new AdminServiceImpl(usersRepository, teachersRepository, passwordEncoder, companyRepository, studentsRepository);
    }

    @Test
    public void checkSaveUsersRepositoryCallingException() {
        String passwordToSave = "123";

        given(usersRepository.save(anyObject())).willThrow(RuntimeException.class);
        given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

        UserForm userForm = new UserForm("12345", "12345", "12345", "ADMIN");

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<String> passwordEncoderArgumentCaptor = ArgumentCaptor.forClass(String.class);

        Assertions.assertThrows(RuntimeException.class, () -> adminService.registerUser(userForm));

        verify(usersRepository).save(argumentCaptor.capture());

        verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

        User userToSave = User.from(userForm);
        userToSave.setPassword(passwordToSave);

        assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
        assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());

    }

    @Test
    public void checkSaveAdminSuccess() {
        String passwordToSave = "123";

        given(usersRepository.save(anyObject())).willReturn(null);
        given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

        UserForm userForm = new UserForm("12345", "12345", "12345", "ADMIN");

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<String> passwordEncoderArgumentCaptor = ArgumentCaptor.forClass(String.class);

        adminService.registerUser(userForm);

        verify(usersRepository).save(argumentCaptor.capture());

        verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

        User userToSave = User.from(userForm);
        userToSave.setPassword(passwordToSave);

        assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
        assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());

    }

//    @Test
//    public void checkUserSaveUsersThrowsIllegalArgument() {
//        UserForm userForm = new UserForm("12345", "12345", "12345", "XXX");
//        Assertions.assertThrows(IllegalArgumentException.class, () -> adminService.registerUser(userForm));
//    }

    @Test
    public void checkSaveTeachersRepositoryException() {
        String passwordToSave = "123";

        given(teachersRepository.save(anyObject())).willThrow(RuntimeException.class);
        given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

        UserForm userForm = new UserForm("12345", "12345", "12345", "TEACHER");

        ArgumentCaptor<Teacher> argumentCaptor = ArgumentCaptor.forClass(Teacher.class);
        ArgumentCaptor<String> passwordEncoderArgumentCaptor = ArgumentCaptor.forClass(String.class);

        Assertions.assertThrows(RuntimeException.class, () -> adminService.registerUser(userForm));

        verify(teachersRepository).save(argumentCaptor.capture());

        verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

        Teacher userToSave = Teacher.fromUserForm(userForm);
        userToSave.setPassword(passwordToSave);

        assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
        assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());
    }

    @Test
    public void checkSaveTeachersSuccess() {
        String passwordToSave = "345";

        given(teachersRepository.save(anyObject())).willReturn(null);
        given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

        UserForm userForm = new UserForm("12345", "12345", "12345", "TEACHER");

        ArgumentCaptor<Teacher> argumentCaptor = ArgumentCaptor.forClass(Teacher.class);
        ArgumentCaptor<String> passwordEncoderArgumentCaptor = ArgumentCaptor.forClass(String.class);

        adminService.registerUser(userForm);

        verify(teachersRepository).save(argumentCaptor.capture());

        verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

        Teacher userToSave = Teacher.fromUserForm(userForm);
        userToSave.setPassword(passwordToSave);

        assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
        assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());
    }

    @Test
    public void checkSaveCompanyRepositoryException() {
        String passwordToSave = "123";

        given(companyRepository.save(anyObject())).willThrow(RuntimeException.class);
        given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

        UserForm userForm = new UserForm("12345", "12345", "12345", "COMPANY");

        ArgumentCaptor<Company> argumentCaptor = ArgumentCaptor.forClass(Company.class);
        ArgumentCaptor<String> passwordEncoderArgumentCaptor = ArgumentCaptor.forClass(String.class);

        Assertions.assertThrows(RuntimeException.class, () -> adminService.registerUser(userForm));

        verify(companyRepository).save(argumentCaptor.capture());

        verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

        Company userToSave = Company.fromUserForm(userForm);
        userToSave.setPassword(passwordToSave);

        assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
        assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());

    }

    @Test
    public void checkSaveCompanySuccess() {
        String passwordToSave = "345";

        given(companyRepository.save(anyObject())).willReturn(null);
        given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

        UserForm userForm = new UserForm("12345", "12345", "12345", "COMPANY");

        ArgumentCaptor<Company> argumentCaptor = ArgumentCaptor.forClass(Company.class);
        ArgumentCaptor<String> passwordEncoderArgumentCaptor = ArgumentCaptor.forClass(String.class);

        adminService.registerUser(userForm);

        verify(companyRepository).save(argumentCaptor.capture());

        verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

        Company userToSave = Company.fromUserForm(userForm);
        userToSave.setPassword(passwordToSave);

        assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
        assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());

    }

    @Test
    public void checkSaveStudentsRepositoryException() {
        String passwordToSave = "123";
        given(studentsRepository.save(anyObject())).willThrow(RuntimeException.class);
        given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);
        UserForm userForm = new UserForm("12345", "12345", "12345", "STUDENT");


        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
        ArgumentCaptor<String> passwordEncoderArgumentCaptor = ArgumentCaptor.forClass(String.class);

        Assertions.assertThrows(RuntimeException.class, () -> adminService.registerUser(userForm));

        verify(studentsRepository).save(argumentCaptor.capture());

        verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

        Student userToSave = Student.fromUserForm(userForm);
        userToSave.setPassword(passwordToSave);

        assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
        assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());

    }

    @Test
    public void checkSaveStudentsSuccess() {
        String passwordToSave = "345";
        given(studentsRepository.save(anyObject())).willReturn(null);
        given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);
        UserForm userForm = new UserForm("12345", "12345", "12345", "STUDENT");


        ArgumentCaptor<Student> argumentCaptor = ArgumentCaptor.forClass(Student.class);
        ArgumentCaptor<String> passwordEncoderArgumentCaptor = ArgumentCaptor.forClass(String.class);

        adminService.registerUser(userForm);

        verify(studentsRepository).save(argumentCaptor.capture());

        verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

        Student userToSave = Student.fromUserForm(userForm);
        userToSave.setPassword(passwordToSave);

        assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
        assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());

    }
}