package com.itis.practice.team123.cvproject.repositories;

import com.itis.practice.team123.cvproject.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacanciesRepository extends JpaRepository<Vacancy, Long> {
}
