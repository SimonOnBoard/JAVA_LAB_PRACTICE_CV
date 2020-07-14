package com.itis.practice.team123.cvproject.repositories;

import com.itis.practice.team123.cvproject.models.ProjectComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectCommentsRepository extends JpaRepository<ProjectComment, Long> {
}
