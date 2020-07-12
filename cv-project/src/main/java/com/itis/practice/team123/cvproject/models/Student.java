package com.itis.practice.team123.cvproject.models;

import com.itis.practice.team123.cvproject.enums.Education;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "students")
public class Student extends User {


    private String firstName;
    private String lastName;
    private String patronymic;

    private int age;

    private String phoneNumber;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Competence> competences;

    @Enumerated(value = EnumType.STRING)
    private Education education;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "students_languages",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Language> languages;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Certificate> certificates;

    private String aboutMe;
}

