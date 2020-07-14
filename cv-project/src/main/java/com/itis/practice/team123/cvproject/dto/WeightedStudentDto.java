package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.models.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeightedStudentDto {
    private Student student;
    private Double weight;
}
