package com.itis.practice.team123.cvproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "competences")
public class Competence {
    @Override
    public String toString() {
        return "Competence{" +
                "id=" + id +
                ", tag=" + tag +
                ", isConfirmed=" + isConfirmed +
                ", confirmedTeachers=" + confirmedTeachers +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "tag_id")
    private Tag tag;

    private boolean isConfirmed;

    @JsonIgnore
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "competences_confirmed_teachers",
            joinColumns = @JoinColumn(name = "competence_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private List<Teacher> confirmedTeachers;
}
