package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.UserForm;

public interface AdminService {
    void registerUser(UserForm userForm) throws IllegalArgumentException;
}
