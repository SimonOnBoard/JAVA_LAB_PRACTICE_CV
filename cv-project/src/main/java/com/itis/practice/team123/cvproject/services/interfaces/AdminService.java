package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.UserDto;
import com.itis.practice.team123.cvproject.dto.UserForm;

public interface AdminService {
    UserDto registerUser(UserForm userForm) throws IllegalArgumentException;
}
