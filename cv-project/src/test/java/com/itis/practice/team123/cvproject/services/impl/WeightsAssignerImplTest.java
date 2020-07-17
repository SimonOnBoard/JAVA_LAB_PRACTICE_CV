package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.WeightedStudentDto;
import com.itis.practice.team123.cvproject.dto.WeightedWorkDto;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.repositories.WorksRepository;
import com.itis.practice.team123.cvproject.services.interfaces.WeightsAssigner;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@SpringBootTest
class WeightsAssignerImplTest {
    @Autowired
    private WeightsAssigner weightsAssigner;
    @MockBean
    private WorksRepository worksRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void assignWorkWeights_Sizes_Equals() {
         List<Work> works = Lists.newArrayList(Work.builder()
                         .tags(Lists.newArrayList(new Tag(1L, "Java"), new Tag(2L, "Python")))
                         .build(),
                 Work.builder()
                         .tags(Lists.newArrayList(new Tag(1L, "Java"), new Tag(2L, "Python")))
                         .build());
         List<WeightedWorkDto> weightedWorks = weightsAssigner.assignWorkWeights(works);
         assertEquals(weightedWorks.size(), works.size());
    }

    @Test
    void assignStudentWeightsByTags_Sizes_Equals() {
        List<Work> works = Lists.newArrayList(Work.builder()
                        .tags(Lists.newArrayList(new Tag(1L, "Java"), new Tag(2L, "Python")))
                        .build(),
                Work.builder()
                        .tags(Lists.newArrayList(new Tag(1L, "Java"), new Tag(2L, "Python")))
                        .build());
        Student student1 = new Student();
        Student student2 = new Student();
        List<Student> students = Lists.newArrayList(student1, student2);
        given(worksRepository.getWorksByStudentAndTags(student1, new Tag(1L, "Java"))).willReturn(works);
        given(worksRepository.getWorksByStudentAndTags(student2, new Tag(1L, "Java"))).willReturn(works);
        List<WeightedStudentDto> weightedStudents =
                weightsAssigner.assignStudentWeightsByTags(students, Lists.newArrayList(new Tag(1L, "Java")));
        assertEquals(weightedStudents.size(), students.size());
    }
}