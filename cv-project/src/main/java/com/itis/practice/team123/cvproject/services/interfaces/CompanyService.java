package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.CompanyDto;
import com.itis.practice.team123.cvproject.dto.CompanyEditForm;
import com.itis.practice.team123.cvproject.dto.PostDto;
import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Post;
import com.itis.practice.team123.cvproject.models.User;

public interface CompanyService {
    CompanyDto updateCompany(CompanyEditForm companyEditForm, Long id) throws IllegalArgumentException;
    CompanyDto updateCompany(CompanyEditForm companyEditForm, Company company);

    Company getCompany(Long id) throws IllegalArgumentException;

    PostDto addPost(Company company, Post post);

    PostDto removePost(Company user, Long postId) throws IllegalArgumentException;
}
