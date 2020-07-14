package com.itis.practice.team123.cvproject.security.providers;

import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.repositories.UsersRepository;
import com.itis.practice.team123.cvproject.security.authentications.JwtAuthentication;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component(value = "prodiver")
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final UsersRepository usersRepository;

    // секретный ключ, которым мы подписываем токен
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();
        Claims claims;
        try {
             claims =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Bad token");
        }
        Long id  = Long.parseLong(claims.get("sub", String.class));
        Optional<User> user = usersRepository.findById(id);
        if(user.isPresent()){
            UserDetailsImpl<?> userDetails = UserDetailsImpl.builder()
                    .user(user.get())
                    .userId(id)
                    .role("ROLE_" + claims.get("role", String.class))
                    .name(claims.get("name", String.class))
                    .build();
            authentication.setAuthenticated(true);
            ((JwtAuthentication)authentication).setUserDetails(userDetails);
        }
        return authentication;
    }

    // проверяет, подходит ли текущий провайдер для этой аутентификации
    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
