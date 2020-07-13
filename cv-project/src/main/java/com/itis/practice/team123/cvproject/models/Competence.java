package com.itis.practice.team123.cvproject.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "student")
@Table(name = "competences")
public class Competence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    private boolean isConfirmed;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "competences_confirmed_teachers",
            joinColumns = @JoinColumn(name = "competence_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> confirmedTeachers;
}
