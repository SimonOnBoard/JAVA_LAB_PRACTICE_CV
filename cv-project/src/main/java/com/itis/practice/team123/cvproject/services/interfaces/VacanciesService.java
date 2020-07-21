package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.VacancyDto;
import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Vacancy;

import java.util.List;

public interface VacanciesService {
    List<Vacancy> getAllVacanciesByCompanyId(Long companyId);
    List<Vacancy> getAllVacanciesForCompany(Company company);
    VacancyDto getVacancy(Long id);

    void addNewVacancy(VacancyDto vacancyDto, Company company);
    void deleteVacancy(Long vacancyId);
}
