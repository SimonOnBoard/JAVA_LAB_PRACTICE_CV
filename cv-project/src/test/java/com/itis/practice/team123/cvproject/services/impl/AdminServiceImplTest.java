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
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
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

    @Nested
    class TestUsersServiceWithUsersRepository{
        private UserForm userForm;
        private String passwordToSave;
        private User userToSave;
        private ArgumentCaptor<User> argumentCaptor;
        private ArgumentCaptor<String> passwordEncoderArgumentCaptor;
        @BeforeEach
        public void beforeUsersRepositoryTests(){
            passwordToSave = "123";
            userForm = new UserForm("12345", "12345", "12345", "ADMIN");
            userToSave = User.from(userForm);
            userToSave.setPassword(passwordToSave);
            argumentCaptor = ArgumentCaptor.forClass(User.class);
           passwordEncoderArgumentCaptor = ArgumentCaptor.forClass(String.class);
        }


        @Test
        public void checkSaveUsersRepositoryCallingException() {

            given(usersRepository.save(any(Teacher.class))).willThrow(RuntimeException.class);
            given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

            Assertions.assertThrows(RuntimeException.class, () -> adminService.registerUser(userForm));

            verify(usersRepository).save(argumentCaptor.capture());

            verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

            assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
            assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());

        }

        @Test
        public void checkSaveAdminSuccess() {

            given(usersRepository.save(any(Teacher.class))).willReturn(null);
            given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

            adminService.registerUser(userForm);

            verify(usersRepository).save(argumentCaptor.capture());

            verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

            assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
            assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());

        }
    }

//    @Test
//    public void checkUserSaveUsersThrowsIllegalArgument() {
//        UserForm userForm = new UserForm("12345", "12345", "12345", "XXX");
//        Assertions.assertThrows(IllegalArgumentException.class, () -> adminService.registerUser(userForm));
//    }

    @Nested
    class TestUsersServiceWithTeachersRepository{
        private UserForm userForm;
        private String passwordToSave;
        private Teacher userToSave;
        private ArgumentCaptor<Teacher> argumentCaptor = ArgumentCaptor.forClass(Teacher.class);
        private ArgumentCaptor<String> passwordEncoderArgumentCaptor = ArgumentCaptor.forClass(String.class);
        @BeforeEach
        public void beforeUsersRepositoryTests(){
            passwordToSave = "123";
            userForm = new UserForm("12345", "12345", "12345", "TEACHER");
            userToSave = Teacher.fromUserForm(userForm);
            userToSave.setPassword(passwordToSave);
            argumentCaptor = ArgumentCaptor.forClass(Teacher.class);
            passwordEncoderArgumentCaptor = ArgumentCaptor.forClass(String.class);
        }


        @Test
        public void checkSaveTeachersRepositoryException() {
            given(teachersRepository.save(any(Teacher.class))).willThrow(RuntimeException.class);
            given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

            Assertions.assertThrows(RuntimeException.class, () -> adminService.registerUser(userForm));

            verify(teachersRepository).save(argumentCaptor.capture());

            verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

            assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
            assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());
        }

        @Test
        public void checkSaveTeachersSuccess() {
            given(teachersRepository.save(any(Teacher.class))).willReturn(null);
            given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

            adminService.registerUser(userForm);

            verify(teachersRepository).save(argumentCaptor.capture());

            verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

            assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
            assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());
        }
    }

    @Nested
    class TestUsersServiceWithCompanyRepository{
        private UserForm userForm;
        private String passwordToSave;
        private Company userToSave;
        private ArgumentCaptor<Company> argumentCaptor;
        private ArgumentCaptor<String> passwordEncoderArgumentCaptor;
        @BeforeEach
        public void beforeUsersRepositoryTests(){
            passwordToSave = "123";
            userForm = new UserForm("12345", "12345", "12345", "COMPANY");
            userToSave = Company.fromUserForm(userForm);
            userToSave.setPassword(passwordToSave);
            argumentCaptor = ArgumentCaptor.forClass(Company.class);
            passwordEncoderArgumentCaptor = ArgumentCaptor.forClass(String.class);
        }


        @Test
        public void checkSaveCompanyRepositoryException() {
            given(companyRepository.save(any(Company.class))).willThrow(RuntimeException.class);
            given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

            Assertions.assertThrows(RuntimeException.class, () -> adminService.registerUser(userForm));

            verify(companyRepository).save(argumentCaptor.capture());

            verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

            assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
            assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());

        }

        @Test
        public void checkSaveCompanySuccess() {

            given(companyRepository.save(any(Company.class))).willReturn(null);
            given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

            adminService.registerUser(userForm);

            verify(companyRepository).save(argumentCaptor.capture());

            verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

            assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
            assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());

        }
    }

    @Nested
    class TestUsersServiceWithStudentsRepository{
        private UserForm userForm;
        private String passwordToSave;
        private Student userToSave;
        private ArgumentCaptor<Student> argumentCaptor;
        private ArgumentCaptor<String> passwordEncoderArgumentCaptor;
        @BeforeEach
        public void beforeUsersRepositoryTests(){
            passwordToSave = "123";
            userForm = new UserForm("12345", "12345", "12345", "STUDENT");
            userToSave = Student.fromUserForm(userForm);
            userToSave.setPassword(passwordToSave);
            argumentCaptor = ArgumentCaptor.forClass(Student.class);
            passwordEncoderArgumentCaptor = ArgumentCaptor.forClass(String.class);
        }

        @Test
        public void checkSaveStudentsRepositoryException() {
            given(studentsRepository.save(any(Student.class))).willThrow(RuntimeException.class);
            given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

            Assertions.assertThrows(RuntimeException.class, () -> adminService.registerUser(userForm));

            verify(studentsRepository).save(argumentCaptor.capture());

            verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

            assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
            assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());

        }

        @Test
        public void checkSaveStudentsSuccess() {
            given(studentsRepository.save(any(Student.class))).willReturn(null);
            given(passwordEncoder.encode(anyString())).willReturn(passwordToSave);

            adminService.registerUser(userForm);

            verify(studentsRepository).save(argumentCaptor.capture());

            verify(passwordEncoder).encode(passwordEncoderArgumentCaptor.capture());

            assertThat(argumentCaptor.getValue()).isEqualTo(userToSave);
            assertThat(passwordEncoderArgumentCaptor.getValue()).isEqualTo(userForm.getPassword());

        }
    }

}