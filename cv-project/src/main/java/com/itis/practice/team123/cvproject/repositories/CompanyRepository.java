package com.itis.practice.team123.cvproject.repositories;

import com.itis.practice.team123.cvproject.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
}
