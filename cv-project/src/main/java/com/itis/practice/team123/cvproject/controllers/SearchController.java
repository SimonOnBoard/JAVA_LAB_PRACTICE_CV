package com.itis.practice.team123.cvproject.controllers;


import com.itis.practice.team123.cvproject.dto.TagFormData;
import com.itis.practice.team123.cvproject.repositories.StudentsRepository;
import com.itis.practice.team123.cvproject.repositories.TagsRepository;
import com.itis.practice.team123.cvproject.services.interfaces.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    TagsRepository tagsRepository;
    @Autowired
    StudentsRepository studentsRepository;
    @Autowired
    StudentsService studentsService;

    @GetMapping("/search")
    public String contractView(Model model) {
        model.addAttribute("tags", tagsRepository.findAll());
        return "search";
    }


    @PostMapping("/search")
    public String compsave(@ModelAttribute("formData") TagFormData formData,
                           Model model) {
        model.addAttribute("tags", tagsRepository.findAll());
        
        StringBuilder sb = new StringBuilder();
        if (formData.getComp() != null)
            formData.getComp().forEach(c -> {sb.append(c + ",");});
        else sb.append("comp is null");
        model.addAttribute("selected", sb.toString());
        model.addAttribute("students", studentsService.getStudentsByTag(formData.getComp()));

        return "search";
    }

}
