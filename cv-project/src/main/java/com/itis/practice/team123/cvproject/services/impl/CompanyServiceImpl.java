package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.CompanyEditForm;
import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Post;
import com.itis.practice.team123.cvproject.models.Teacher;
import com.itis.practice.team123.cvproject.repositories.CompanyRepository;
import com.itis.practice.team123.cvproject.services.interfaces.CompanyService;
import com.itis.practice.team123.cvproject.services.interfaces.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public void updateCompany(CompanyEditForm companyEditForm, Long id) throws IllegalArgumentException {
        Company company = getCompany(id);
        company.setAddress(companyEditForm.getAddress());
        company.setPhone(companyEditForm.getPhone());
        company.setDescription(companyEditForm.getDescription());
        company.setName(companyEditForm.getName());
    }


    @Override
    public Company getCompany(Long id) throws IllegalArgumentException {
        return companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    @Transactional
    public void addPost(Company company, Post post) {
        postRepository.save(post);
        company.getPosts().add(post);
    }

    @Override
    @Transactional
    public void removePost(Company company, Long postId) throws IllegalArgumentException {
        Post post = postRepository.getById(postId);
        company.getPosts().remove(post);
        postRepository.delete(post);
    }
}
