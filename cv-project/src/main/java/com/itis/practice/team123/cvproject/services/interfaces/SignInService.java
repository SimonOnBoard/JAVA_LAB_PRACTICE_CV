package com.itis.practice.team123.cvproject.services.interfaces;


import com.itis.practice.team123.cvproject.dto.SignInDto;
import com.itis.practice.team123.cvproject.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInData);
}
