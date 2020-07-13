package com.itis.practice.team123.cvproject.security.authentications;

import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class JwtAuthentication implements Authentication {
    private boolean isAuthenticated = false;
    // токен
    private String token;
    // информация о пользователе
    private UserDetailsImpl<?> userDetails;

    public JwtAuthentication(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return this.token;
    }

    public void setUserDetails(UserDetailsImpl<?> userDetails) {
        this.userDetails = userDetails;
    }

}
