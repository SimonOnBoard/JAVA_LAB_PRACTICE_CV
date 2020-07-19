package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.FilterFormData;
import com.itis.practice.team123.cvproject.dto.WeightedStudentDto;
import com.itis.practice.team123.cvproject.models.Student;

import java.util.List;

public interface SearchService {
    List<Student> getStudentsByTag(List<String> tags_name);
    List<WeightedStudentDto> getStudentsByFilters(FilterFormData filterFormData);
}
