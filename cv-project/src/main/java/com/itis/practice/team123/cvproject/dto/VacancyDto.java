package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.models.Vacancy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VacancyDto {
    private String title;
    private Integer salaryMin;
    private Integer salaryMax;
    private String description;
    private Set<String> addresses;
    private Set<String> phoneNumbers;
    private Set<String> emails;
    private List<String> tags;

    public static VacancyDto from(Vacancy vacancy) {
        VacancyDtoBuilder builder = VacancyDto.builder()
                .title(vacancy.getTitle())
                .description(vacancy.getDescription())
                .addresses(vacancy.getAddresses())
                .phoneNumbers(vacancy.getPhoneNumbers())
                .emails(vacancy.getEmails())
                .salaryMin(vacancy.getSalaryMin())
                .salaryMax(vacancy.getSalaryMax())
                .tags(vacancy.getTags().stream()
                        .map(Tag::getName)
                        .collect(Collectors.toList())
                );
//        Integer salaryMin = vacancy.getSalaryMin();
//        Integer salaryMax = vacancy.getSalaryMax();
//        if (salaryMin != null) builder.salaryMin(salaryMin);
//        if (salaryMax != null) builder.salaryMax(salaryMax);
        return builder.build();
    }
}
