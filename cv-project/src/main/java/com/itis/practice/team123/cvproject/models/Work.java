package com.itis.practice.team123.cvproject.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
public class Work {
    private String name;
    private String description;
    private List<Tag> tags;
    private Student student;
}
