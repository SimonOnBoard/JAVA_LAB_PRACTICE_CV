package com.itis.practice.team123.cvproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.repositories.WorksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class StudentWorksControllerTest {
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
//
//    @Test
//    void getStudentsWorks() {
//        when(worksRepository.getWorksByStudentId(anyLong())).thenReturn();
//    }
}