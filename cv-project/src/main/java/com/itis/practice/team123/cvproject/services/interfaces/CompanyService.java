package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.CompanyEditForm;
import com.itis.practice.team123.cvproject.models.Company;

public interface CompanyService {
    void updateCompany(CompanyEditForm companyEditForm, Long id) throws IllegalArgumentException;

    Company getCompany(Long id) throws IllegalArgumentException;
}
