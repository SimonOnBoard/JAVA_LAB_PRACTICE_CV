package com.itis.practice.team123.cvproject.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ElementCollection
    private List<String> links;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "tag_id")
    private List<Tag> tags;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}