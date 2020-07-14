package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.CompanyEditForm;
import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Post;
import com.itis.practice.team123.cvproject.models.User;

public interface CompanyService {
    void updateCompany(CompanyEditForm companyEditForm, Long id) throws IllegalArgumentException;

    Company getCompany(Long id) throws IllegalArgumentException;

    void addPost(Company company, Post post);

    void removePost(Company user, Long postId) throws IllegalArgumentException;
}
