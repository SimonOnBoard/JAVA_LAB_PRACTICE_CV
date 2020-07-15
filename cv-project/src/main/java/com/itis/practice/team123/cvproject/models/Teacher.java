package com.itis.practice.team123.cvproject.models;

import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Teacher extends User {
    private String surname;
    private String name;
    private String patronymic;
    private String institution;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "teachers_languages",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<Language> languages;

//    private List<String> positions;
//    private List<String> educations;
//    private List<String> academicDegrees;

    private String additionalInfo;

    public Teacher(Long id, String username, String password, Role role, String email, String description) {
        super(id, username, password, role, email);
        this.additionalInfo = description;
    }

    public Teacher(Long id, String username, String password, Role role, String email) {
        super(id, username, password, role, email);
    }

    public Teacher(Long id) {
        this.id = id;
    }

    public static Teacher fromUserForm(UserForm userForm) {
        return new Teacher((Long) null, userForm.getUsername(), userForm.getPassword(), userForm.getRole(), userForm.getEmail());
    }
}
