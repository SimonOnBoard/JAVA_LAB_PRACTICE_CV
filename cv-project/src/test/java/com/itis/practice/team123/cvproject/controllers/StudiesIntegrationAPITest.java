package com.itis.practice.team123.cvproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.practice.team123.cvproject.CvProjectApplication;
import com.itis.practice.team123.cvproject.dto.BearerDto;
import com.itis.practice.team123.cvproject.dto.WorkDto;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.repositories.StudentsRepository;
import com.itis.practice.team123.cvproject.repositories.TagsRepository;
import com.itis.practice.team123.cvproject.repositories.TeachersRepository;
import com.itis.practice.team123.cvproject.repositories.WorksRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudiesIntegrationAPITest {
    @MockBean
    private TeachersRepository teachersRepository;
    @MockBean
    private StudentsRepository studentsRepository;
    @MockBean
    private TagsRepository tagsRepository;
    @MockBean
    private WorksRepository worksRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Value("${bearer.token}")
    private String bearerToken;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        worksRepository = mock(WorksRepository.class);
    }

    @Test
    void addWork_Return_Ok() {
        WorkDto workDto = WorkDto.builder()
                .description("description")
                .title("title")
                .tags(Lists.newArrayList("Java"))
                .studentId(2L)
                .teacherId(1L)
                .build();
        BearerDto bearerDto = new BearerDto(bearerToken, workDto);
        when(teachersRepository.getOne(anyLong())).thenReturn(new Teacher());
        when(studentsRepository.getOne(anyLong())).thenReturn(new Student());
        when(tagsRepository.findAllByNameIn(anyList())).thenReturn(new ArrayList<>());
        when(worksRepository.save(any(Work.class))).thenReturn(new Work());
        try {
            mockMvc.perform(post("/api/works")
                    .content(objectMapper.writeValueAsString(bearerDto)))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    void addWork_Return_4xx() {
//        WorkDto workDto = WorkDto.builder()
//                .description("description")
//                .title("title")
//                .tags(Lists.newArrayList("Java"))
//                .studentId(null)
//                .teacherId(null)
//                .build();
//        when(teachersRepository.getOne(anyLong())).thenReturn(new Teacher());
//        when(studentsRepository.getOne(anyLong())).thenReturn(new Student());
//        when(tagsRepository.findAllByNameIn(anyList())).thenReturn(new ArrayList<>());
//        when(worksRepository.save(any(Work.class))).thenThrow(IllegalArgumentException.class);
//        try {
//            mockMvc.perform(post("/api/works")
//                    .content(objectMapper.writeValueAsString(workDto)))
//                    .andExpect(status().is4xxClientError());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}