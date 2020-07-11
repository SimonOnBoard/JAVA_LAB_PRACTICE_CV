package com.itis.practice.team123.cvproject.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Teacher extends User {
    private String surname;
    private String name;
    private String patronymic;
    private String institution;
    private List<String> positions;
    private List<String> educations;
    private List<String> academicDegrees;
    private List<String> languages;
    private String additionalInfo;
}
