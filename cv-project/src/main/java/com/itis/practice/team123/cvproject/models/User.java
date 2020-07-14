package com.itis.practice.team123.cvproject.models;

import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString(exclude = {"username", "password", "role"})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(unique = true)
    protected String username;

    protected String password;

    protected Role role;

    protected String email;

    public static User from(UserForm userForm) {
        return User.builder()
                .username(userForm.getUsername())
                .password(userForm.getPassword())
                .email(userForm.getEmail())
                .role(userForm.getRole())
                .build();
    }
}
