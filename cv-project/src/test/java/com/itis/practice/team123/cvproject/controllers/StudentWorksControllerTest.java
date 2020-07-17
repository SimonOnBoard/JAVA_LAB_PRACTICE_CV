package com.itis.practice.team123.cvproject.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.practice.team123.cvproject.dto.WeightedWorkDto;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.repositories.WorksRepository;
import com.itis.practice.team123.cvproject.services.interfaces.WeightsAssigner;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentWorksControllerTest {
    @Mock
    private WorksRepository worksRepository;
    @Mock
    private WeightsAssigner weightsAssigner;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        worksRepository = mock(WorksRepository.class);
        weightsAssigner = mock(WeightsAssigner.class);
    }

//    @Test
//    @WithMockUser
//    void getStudentsWorks() {
//        when(worksRepository.getWorksByStudentId(anyLong())).thenReturn(Lists.newArrayList(new Work()));
//        when(weightsAssigner.assignWorkWeights(anyList())).thenReturn(Lists.newArrayList(new WeightedWorkDto()));
//        try {
//            mockMvc.perform(get("/works/111"))
//                    .andExpect(status().isOk());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}