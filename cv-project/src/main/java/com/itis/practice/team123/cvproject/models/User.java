package com.itis.practice.team123.cvproject.models;

import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.enums.Role;
import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
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

        return new User((Long)null, userForm.getUsername(), userForm.getPassword(), userForm.getRole(), userForm.getEmail());
    }
}
