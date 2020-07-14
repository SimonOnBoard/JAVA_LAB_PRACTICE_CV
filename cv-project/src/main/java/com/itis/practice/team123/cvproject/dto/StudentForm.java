package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.models.Student;
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
    private String email;
    private String aboutMe;

    public static StudentForm from(Student student) {
        return StudentForm.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .patronymic(student.getPatronymic())
                .age(student.getAge())
                .phoneNumber(student.getPhoneNumber())
                .email(student.getEmail())
                .aboutMe(student.getAboutMe())
                .build();
    }
}
