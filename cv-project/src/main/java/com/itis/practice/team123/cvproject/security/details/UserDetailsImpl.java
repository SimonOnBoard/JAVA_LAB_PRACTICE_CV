package com.itis.practice.team123.cvproject.security.details;

import com.itis.practice.team123.cvproject.models.User;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.Collection;

@Builder
public class UserDetailsImpl<T extends User> implements org.springframework.security.core.userdetails.UserDetails {
    private Long userId;
    private String role;
    private String name;

    private T user;

    public Long getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;

    }

    @Override
    public boolean isAccountNonLocked() {
        return true;

    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
