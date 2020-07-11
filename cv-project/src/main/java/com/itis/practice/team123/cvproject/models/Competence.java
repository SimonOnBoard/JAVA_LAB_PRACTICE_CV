package com.itis.practice.team123.cvproject.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
public class Competence {
    private Tag tag;
    private boolean approved;
    private List<Teacher> teachersApproved;
    private Student student;
}
