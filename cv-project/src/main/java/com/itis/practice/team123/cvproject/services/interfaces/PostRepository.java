package com.itis.practice.team123.cvproject.services.interfaces;


import com.itis.practice.team123.cvproject.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PostRepository extends JpaRepository<Post,Long> {
    Post getById(Long postId);
}
