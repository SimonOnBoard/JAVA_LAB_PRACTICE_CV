package com.itis.practice.team123.cvproject.controllers;


import com.itis.practice.team123.cvproject.dto.TagFormData;
import com.itis.practice.team123.cvproject.dto.WeightedStudentDto;
import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.repositories.TagsRepository;
import com.itis.practice.team123.cvproject.services.interfaces.StudentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class SearchController {

    private TagsRepository tagsRepository;
    private StudentsService studentsService;

    public SearchController(TagsRepository tagsRepository, StudentsService studentsService) {
        this.tagsRepository = tagsRepository;
        this.studentsService = studentsService;
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = {"/api/search"})
    public @ResponseBody ResponseEntity<List<Tag>> tagsView() {
        return ResponseEntity.ok(tagsRepository.findAll());
    }

    @PreAuthorize("permitAll()")
    @PostMapping(value = {"/search", "/api/search"})
    public @ResponseBody ResponseEntity<List<WeightedStudentDto>> competenceSave(TagFormData formData) {
        return ResponseEntity.ok(studentsService.getStudentsByTag(formData.getComp()));
    }

    @PreAuthorize("permitAll()")
    @GetMapping(value = {"/search"})
    public String searchView() {
        return "search";
    }

}
