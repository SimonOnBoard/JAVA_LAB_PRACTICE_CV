package com.itis.practice.team123.cvproject.models;

import com.itis.practice.team123.cvproject.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends User {
    private String surname;
    private String name;
    private String patronymic;
    private String institution;
//    private List<String> positions;
//    private List<String> educations;
//    private List<String> academicDegrees;
//    private List<String> languages;
    private String additionalInfo;
    private String description;
    public Teacher(Long id, String username, String password, Role role, String email, String description) {
        super(id, username, password, role, email);
        this.description = description;
    }

}
