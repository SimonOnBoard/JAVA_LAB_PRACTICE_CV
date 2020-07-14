package com.itis.practice.team123.cvproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_title")
    private String title;

    private String description;

    @ElementCollection
    private List<String> links;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id")
    private Student owner;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    private List<ProjectComment> projectComments;
}
