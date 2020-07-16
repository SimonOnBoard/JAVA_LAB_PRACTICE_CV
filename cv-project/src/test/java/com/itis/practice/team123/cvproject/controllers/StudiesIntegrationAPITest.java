package com.itis.practice.team123.cvproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.practice.team123.cvproject.CvProjectApplication;
import com.itis.practice.team123.cvproject.dto.WorkDto;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.repositories.WorksRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudiesIntegrationAPITest {
    private WorksRepository worksRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

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
        when(worksRepository.save(any(Work.class))).thenReturn(new Work());
        try {
            mockMvc.perform(post("/api/works")
                    .content(objectMapper.writeValueAsString(workDto)))
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
//        when(worksRepository.save(any(Work.class))).thenThrow(RuntimeException.class);
//        try {
//            mockMvc.perform(post("/api/works")
//                    .content(objectMapper.writeValueAsString(workDto)))
//                    .andExpect(status().is4xxClientError());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}