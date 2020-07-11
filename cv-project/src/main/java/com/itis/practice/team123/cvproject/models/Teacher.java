package com.itis.practice.team123.cvproject.models;

import com.itis.practice.team123.cvproject.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

@Entity
@Table(name = "TEACHERS")
@NoArgsConstructor
public class Teacher extends User {
    protected String description;

    public Teacher(Long id, String username, String password, Role role, String email, String description) {
        super(id, username, password, role, email);
        this.description = description;
    }
}
