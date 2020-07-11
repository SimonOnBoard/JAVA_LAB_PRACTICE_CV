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
public class Competence {
    private Tag tag;
    private boolean approved;
    private List<Teacher> teachersApproved;
    private Student student;
}
