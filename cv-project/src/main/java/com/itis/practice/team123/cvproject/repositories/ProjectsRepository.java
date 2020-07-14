package com.itis.practice.team123.cvproject.repositories;

import com.itis.practice.team123.cvproject.models.Project;
import com.itis.practice.team123.cvproject.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectsRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByOwner(Student student);
}
