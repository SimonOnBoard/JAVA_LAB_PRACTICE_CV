package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.SignInDto;
import com.itis.practice.team123.cvproject.dto.TokenDto;
import com.itis.practice.team123.cvproject.enums.Role;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.repositories.UsersRepository;
import com.itis.practice.team123.cvproject.services.interfaces.SignInService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SignInServiceImplTest {
    @Autowired
    private SignInService signInService;
    @MockBean
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.secret}")
    private String secret;

    @BeforeEach
    void setUp() {
    }

    @Test
    void signIn_Token_Check() {
        User user = new User();
        user.setId(1L);
        user.setUsername("username");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRole(Role.ADMIN);
        SignInDto signInDto = SignInDto.builder()
                .login("username")
                .password("password")
                .build();
        String token = Jwts.builder()
                .setSubject(user.getId().toString()) // id пользователя
                .claim("name", user.getUsername()) // имя
                .claim("role", user.getRole().name()) // роль
                .signWith(SignatureAlgorithm.HS256, secret) // подписываем его с нашим secret
                .compact();
        when(usersRepository.findByUsername(signInDto.getLogin())).thenReturn(Optional.of(user));
        TokenDto tokenDto = signInService.signIn(signInDto);
        assertEquals(token, tokenDto.getToken());
    }
}