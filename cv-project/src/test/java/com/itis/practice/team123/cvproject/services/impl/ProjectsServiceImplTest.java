package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.ProjectCommentDto;
import com.itis.practice.team123.cvproject.dto.ProjectDto;
import com.itis.practice.team123.cvproject.models.Project;
import com.itis.practice.team123.cvproject.models.ProjectComment;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.repositories.ProjectCommentsRepository;
import com.itis.practice.team123.cvproject.repositories.ProjectsRepository;
import com.itis.practice.team123.cvproject.repositories.StudentsRepository;
import com.itis.practice.team123.cvproject.services.interfaces.ProjectsService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest
class ProjectsServiceImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProjectsService projectsService;
    @MockBean
    private StudentsRepository studentsRepository;
    @MockBean
    private ProjectsRepository projectsRepository;
    @MockBean
    private ProjectCommentsRepository projectCommentsRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllProjects_isOk_And_Sizes_Equal() {

        when(studentsRepository.getOne(anyLong())).thenReturn(new Student());
        when(projectsRepository.findAllByOwner(any(Student.class))).thenReturn(Lists.newArrayList(new Project(), new Project()));
        List<Project> projects = projectsService.getAllProjects(1L);
        assertEquals(projects.size(), 2);
    }

    @Test
    void getProjectById_DoesNot_Throw_Exception() {
        when(projectsRepository.getOne(anyLong())).thenReturn(new Project());
        projectsService.getProjectById(1L);
        assertDoesNotThrow((ThrowingSupplier<Exception>) Exception::new);
    }

    @Test
    void addNewProject_DoesNot_Throw_Exception() {
        Student student = new Student();
        ProjectDto projectDto = new ProjectDto();
        when(projectsRepository.save(any(Project.class))).thenReturn(new Project());
        projectsService.addNewProject(projectDto, student);
        assertDoesNotThrow((ThrowingSupplier<Exception>) Exception::new);
    }

    @Test
    void addNewProject_DoesNot_Throw_Exception_On_Casting() {
        Student student = new Student();
        ProjectDto projectDto = new ProjectDto();
        when(projectsRepository.save(any(Project.class))).thenReturn(new Project());
        projectsService.addNewProject(projectDto, student);
        assertDoesNotThrow((ThrowingSupplier<ClassCastException>) ClassCastException::new);
    }

    @Test
    void addNewProject_Does_Throw_Exception_On_Casting() {
        Teacher teacher = new Teacher();
        ProjectDto projectDto = new ProjectDto();
        when(projectsRepository.save(any(Project.class))).thenReturn(new Project());
        assertThrows(ClassCastException.class, () -> projectsService.addNewProject(projectDto, teacher));
    }

    @Test
    void commentProject_DoesNot_Throw_Exception() {
        when(projectCommentsRepository.save(any(ProjectComment.class))).thenReturn(new ProjectComment());
        when(projectsRepository.getOne(anyLong())).thenReturn(Project.builder().id(1L).build());
        projectsService.commentProject(new ProjectCommentDto(), 1L, new Teacher());
        assertDoesNotThrow((ThrowingSupplier<Exception>) Exception::new);
    }

    @Test
    void commentProject_DoesNot_Throw_Exception_On_Casting() {
        Teacher teacher = new Teacher();
        ProjectCommentDto projectCommentDto = new ProjectCommentDto();
        when(projectCommentsRepository.save(any(ProjectComment.class))).thenReturn(new ProjectComment());
        when(projectsRepository.getOne(anyLong())).thenReturn(Project.builder().id(1L).build());
        projectsService.commentProject(projectCommentDto, 1L, teacher);
        assertDoesNotThrow((ThrowingSupplier<ClassCastException>) ClassCastException::new);
    }

    @Test
    void commentProject_Does_Throw_Exception_On_Casting() {
        Student student = new Student();
        ProjectCommentDto projectCommentDto = new ProjectCommentDto();
        when(projectCommentsRepository.save(any(ProjectComment.class))).thenReturn(new ProjectComment());
        when(projectsRepository.getOne(anyLong())).thenReturn(Project.builder().id(1L).build());
        assertThrows(ClassCastException.class, () -> projectsService.commentProject(projectCommentDto, 1L, student));
    }
}