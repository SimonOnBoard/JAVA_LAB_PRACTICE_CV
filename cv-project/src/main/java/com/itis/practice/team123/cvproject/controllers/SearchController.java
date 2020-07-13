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

    private TagsRepository tagsRepository;
    private StudentsRepository studentsRepository;
    private StudentsService studentsService;

    public SearchController(TagsRepository tagsRepository, StudentsRepository studentsRepository, StudentsService studentsService) {
        this.tagsRepository = tagsRepository;
        this.studentsRepository = studentsRepository;
        this.studentsService = studentsService;
    }


    @GetMapping("/search")
    public String contractView(Model model) {
        model.addAttribute("tags", tagsRepository.findAll());
        return "search";
    }


    @PostMapping("/search")
    public String competenceSave(@ModelAttribute("formData") TagFormData formData,
                                 Model model) {
        model.addAttribute("tags", tagsRepository.findAll());
        //переписать кусок кода нельзя создавать объект нельзя писать бизнес логику в контроллере
        StringBuilder sb = new StringBuilder();
        if (formData.getCompetencies() != null)
            formData.getCompetencies().forEach(c ->
                    sb.append(c).append(",")
            );
        else sb.append("comp is null");
        model.addAttribute("selected", sb.toString());
        model.addAttribute("students", studentsService.getStudentsByTag(formData.getCompetencies()));

        return "search";
    }

}
