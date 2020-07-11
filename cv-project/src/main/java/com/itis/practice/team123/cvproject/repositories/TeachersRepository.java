package com.itis.practice.team123.cvproject.repositories;

import com.itis.practice.team123.cvproject.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface TeachersRepository extends JpaRepository<Teacher,Long> {
}
