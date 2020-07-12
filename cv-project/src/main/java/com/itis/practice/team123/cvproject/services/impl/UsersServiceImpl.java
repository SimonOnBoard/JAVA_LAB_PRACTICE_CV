package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.repositories.UsersRepository;
import com.itis.practice.team123.cvproject.services.interfaces.UsersService;
import com.itis.practice.team123.cvproject.utils.Initializer;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public User getUser(Long id) throws IllegalArgumentException {
        return usersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
