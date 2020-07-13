package com.itis.practice.team123.cvproject.models;

import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.enums.Role;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company extends User{

    private String name;

    private String description;

    private String address;

    private String phone;

    public Company(Long id, String username, String password, Role role, String email) {
        super(id, username, password, role, email);
    }

    public static Company fromUserForm(UserForm userForm) {
        return new Company((Long) null, userForm.getUsername(), userForm.getPassword(), userForm.getRole(), userForm.getEmail());
    }
}
