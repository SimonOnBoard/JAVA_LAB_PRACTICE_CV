package com.itis.practice.team123.cvproject.repositories;

import com.itis.practice.team123.cvproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String userName);
}
