package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.models.Language;
import com.itis.practice.team123.cvproject.models.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherDto {
    private String name;
    private String surname;
    private String patronymic;
    private String institution;
    private String info;
    private String mail;
    private List<Language> languageList;
    public static TeacherDto from(Teacher user) {
        return TeacherDto.builder().name(user.getName())
                .surname(user.getSurname())
                .patronymic(user.getPatronymic())
                .institution(user.getInstitution())
                .info(user.getAdditionalInfo())
                .languageList(user.getLanguages())
                .mail(user.getEmail())
                .build();
    }
}
