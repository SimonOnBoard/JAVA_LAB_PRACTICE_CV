package com.itis.practice.team123.cvproject.models;

import com.itis.practice.team123.cvproject.enums.Education;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String patronymic;

    private int age;

    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Competence> competences;

    @Enumerated(value = EnumType.STRING)
    private Education education;
    private List<Language> languages;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Certificate> certificates;

    private String aboutMe;
}
