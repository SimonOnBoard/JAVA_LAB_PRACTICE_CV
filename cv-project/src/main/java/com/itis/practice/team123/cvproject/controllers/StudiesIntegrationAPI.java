package com.itis.practice.team123.cvproject.controllers;

import com.itis.practice.team123.cvproject.dto.WorkDto;
import com.itis.practice.team123.cvproject.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/works")
public class StudiesIntegrationAPI {

    @PostMapping("/")
    public WorkDto addWork(@RequestBody WorkDto workDto) {

        return workDto;
    }
}
