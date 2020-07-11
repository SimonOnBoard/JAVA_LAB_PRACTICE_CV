package com.itis.practice.team123.cvproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkDto {
    private String name;
    private String description;
    private List<Tag> tags;
    private Long studentId;
    private Long teacherId;
}
