package com.itis.practice.team123.cvproject.models;

import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company extends User{

    private String name;

    private String description;

    private String address;

    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
    private List<Post> posts;

    public Company(Long id, String username, String password, Role role, String email) {
        super(id, username, password, role, email);
    }

    public static Company fromUserForm(UserForm userForm) {
        return new Company((Long) null, userForm.getUsername(), userForm.getPassword(), userForm.getRole(), userForm.getEmail());
    }
}
