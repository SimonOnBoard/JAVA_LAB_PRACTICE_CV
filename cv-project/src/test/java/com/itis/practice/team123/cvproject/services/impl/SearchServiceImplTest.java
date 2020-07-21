package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.FilterFormData;
import com.itis.practice.team123.cvproject.dto.WeightedStudentDto;
import com.itis.practice.team123.cvproject.enums.Education;
import com.itis.practice.team123.cvproject.models.Competence;
import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.repositories.StudentsRepository;
import com.itis.practice.team123.cvproject.repositories.TagsRepository;
import com.itis.practice.team123.cvproject.services.interfaces.LanguageService;
import com.itis.practice.team123.cvproject.services.interfaces.SearchService;
import com.itis.practice.team123.cvproject.services.interfaces.WeightsAssigner;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.ThrowingSupplier;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;


@ExtendWith(MockitoExtension.class)
public class SearchServiceImplTest {
    @Mock
    private TagsRepository tagsRepository;
    @Mock
    private StudentsRepository studentsRepository;
    @Mock
    private WeightsAssigner weightsAssigner;
    @Mock
    private LanguageService languageService;

    @InjectMocks
    private SearchServiceImpl searchService;

    @Test
    void checkStudentHasTags() {
        List<String> tags = Arrays.asList("java", "jdbc");
        String tag = "java";
        List<Student> students = searchService.getStudentsByTag(tags);
        boolean flag = false;
        for (Student student : students) {
            for (Competence competence : student.getCompetences()) {
                if (competence.getTag().getName().equals(tag)) flag = true;
            }
            assertTrue(flag);
        }
    }

    @Test
    void checkStudentsEducation() {
        Education education = Education.GRADUATE;
        FilterFormData formData = FilterFormData.builder()
                .education(Collections.singletonList(education.toString()))
                .build();
        List<WeightedStudentDto> students = searchService.getStudentsByFilters(formData);
        for (WeightedStudentDto student : students) {
            assertSame(student.getStudent().getEducation(), education);
        }

    }
    @Test
    void checkIfFailsWhenNoData() {
        searchService.getStudentsByFilters(FilterFormData.builder().build());
        assertDoesNotThrow((ThrowingSupplier<Exception>) Exception::new);
    }

    @Test
    void checkIfLangsAreSame() {
        List<Language> languages = new ArrayList<>();
        List<String> dataLanguage = Arrays.asList("english B1", "japan C2");


        FilterFormData formData = FilterFormData.builder()
                .language(dataLanguage)
                .build();

        for (String lang : dataLanguage) {
            languages.add(languageService.getLanguageByNameAndLevel(lang));
        }

        List<WeightedStudentDto> students = searchService.getStudentsByFilters(formData);
        for (WeightedStudentDto student : students) {
            assertTrue(student.getStudent().getLanguages().containsAll(languages));
        }


    }
}
