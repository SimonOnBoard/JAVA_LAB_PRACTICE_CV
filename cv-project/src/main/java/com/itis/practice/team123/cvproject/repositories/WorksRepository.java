package com.itis.practice.team123.cvproject.repositories;

import com.itis.practice.team123.cvproject.models.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface WorksRepository extends JpaRepository<Work, Long> {
}
