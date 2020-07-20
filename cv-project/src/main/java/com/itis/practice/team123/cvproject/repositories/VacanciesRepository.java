package com.itis.practice.team123.cvproject.repositories;

import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacanciesRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> getAllByCompany(Company company);
}
