package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.VacancyDto;
import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.models.Vacancy;
import com.itis.practice.team123.cvproject.repositories.CompanyRepository;
import com.itis.practice.team123.cvproject.repositories.TagsRepository;
import com.itis.practice.team123.cvproject.repositories.VacanciesRepository;
import com.itis.practice.team123.cvproject.services.interfaces.VacanciesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacanciesServiceImpl implements VacanciesService {

    private final VacanciesRepository vacanciesRepository;
    private final CompanyRepository companyRepository;
    private final TagsRepository tagsRepository;

    @Override
    public List<Vacancy> getAllVacanciesForCompany(Long companyId) {
        Company company = companyRepository.getOne(companyId);
        return vacanciesRepository.getAllByCompany(company);
    }

    @Override
    public VacancyDto getVacancy(Long id) {
        return VacancyDto.from(vacanciesRepository.getOne(id));
    }

    @Override
    public void addNewVacancy(VacancyDto vacancyDto, Company company) {
        vacanciesRepository.save(Vacancy.builder()
                .title(vacancyDto.getTitle())
                .description(vacancyDto.getDescription())
                .tags(getTags(vacancyDto.getTags()))
                .addresses(vacancyDto.getAddresses())
                .emails(vacancyDto.getEmails())
                .phoneNumbers(vacancyDto.getPhoneNumbers())
                .company(company)
                .salaryMax(vacancyDto.getSalaryMax())
                .salaryMin(vacancyDto.getSalaryMin())
                .build());
    }

    @Override
    public void deleteVacancy(Long vacancyId) {
        vacanciesRepository.deleteById(vacancyId);
    }

    private List<Tag> getTags(List<String> tagNames) {
        return tagsRepository.findAllByNameIn(tagNames);
    }
}
