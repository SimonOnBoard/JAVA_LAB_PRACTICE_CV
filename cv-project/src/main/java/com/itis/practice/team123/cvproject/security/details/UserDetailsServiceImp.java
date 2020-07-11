package com.itis.practice.team123.cvproject.security.details;

import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.repositories.UsersRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    private UsersRepository usersRepository;

    public UserDetailsServiceImp(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetailsImpl<?> loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return UserDetailsImpl.builder()
                .user(user)
                .userId(user.getId())
                .role("ROLE_" + user.getRole())
                .name(username)
                .build();
    }
}
