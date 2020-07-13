package com.itis.practice.team123.cvproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherEditForm {
    private String name;
    private String patronymic;
    private String surname;
    private String institution;
    private String info;
}
