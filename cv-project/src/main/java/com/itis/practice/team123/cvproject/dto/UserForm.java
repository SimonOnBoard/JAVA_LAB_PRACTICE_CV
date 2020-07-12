package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserForm {
    private String username;
    private String password;
    private String email;
    private Role role;

    public UserForm(String username, String password, String email, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = Role.valueOf(role);
    }

    public Role getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = Role.valueOf(role);
    }
}
