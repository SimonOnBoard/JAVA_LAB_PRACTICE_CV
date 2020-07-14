package com.itis.practice.team123.cvproject.repositories;

import com.itis.practice.team123.cvproject.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
}
