package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.models.User;

public interface UsersService {
    User getUser(Long id) throws IllegalArgumentException;
}
