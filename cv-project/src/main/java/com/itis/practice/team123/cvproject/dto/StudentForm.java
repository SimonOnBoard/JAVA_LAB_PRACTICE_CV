package com.itis.practice.team123.cvproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentForm {

    private String firstName;
    private String lastName;
    private String patronymic;
    private int age;
    private String phoneNumber;
    private String aboutMe;

}
