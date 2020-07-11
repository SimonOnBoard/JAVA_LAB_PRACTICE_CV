package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.models.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Work {
    private String name;
    private String description;
    private List<Tag> tags;
    private Student student;
    private Teacher teacher;
}
