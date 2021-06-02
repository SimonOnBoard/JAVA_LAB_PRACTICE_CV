package com.itis.practice.team123.cvproject.testcontainers;


import com.itis.practice.team123.cvproject.CvProjectApplication;
import com.itis.practice.team123.cvproject.CvProjectApplicationTests;
import com.itis.practice.team123.cvproject.SecurityConfiguration;
import com.itis.practice.team123.cvproject.TestCheckConfiguration;
import com.itis.practice.team123.cvproject.models.Vacancy;
import com.itis.practice.team123.cvproject.repositories.VacanciesRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(classes = {CvProjectApplication.class,
        CvProjectApplicationTests.class,
        SecurityConfiguration.class},
initializers = TestCheckConfiguration.Initializer.class)
@AutoConfigureMockMvc
public class VacanciesIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private VacanciesRepository vacanciesRepository;

    @Before
    public void init() {
        log.info("SAVING TO REPO");
        vacanciesRepository
                .save(Vacancy.builder()
                        .emails(new HashSet<>(List.of("werer")))
                        .addresses(new HashSet<>(List.of("fwer")))
                        .title("New vacancy")
                        .description("erwqrwerqwer")
                        .build());
        vacanciesRepository
                .save(Vacancy.builder()
                        .emails(new HashSet<>(List.of("werer")))
                        .addresses(new HashSet<>(List.of("fwer")))
                        .title("Another one")
                        .description("Ещё одна супер вакансия")
                        .build());
    }

    @SneakyThrows
    @Test
    public void checkCompanyController() {
        log.info("EXECUTING TEST");
        ResultActions resultActions = mockMvc.perform(get("/vacancies"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Ещё одна супер вакансия")));
        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        writeResponse(contentAsString);
    }

    private void writeResponse(String contentAsString) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("result.html"));
        writer.write(contentAsString);
        writer.close();
    }

    @After
    public void destroy() {
        log.info("CLEARING ALL CONTEXT");
        vacanciesRepository.deleteAll();
    }

}
