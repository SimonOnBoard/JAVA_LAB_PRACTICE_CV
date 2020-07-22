package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.models.Project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectDto {
    private String title;
    private String description;
    private List<String> links;
    private Long id;
    public static ProjectDto from(Project project) {
        return ProjectDto.builder()
                .description(project.getDescription())
                .links(project.getLinks())
                .title(project.getTitle())
                .id(project.getId())
                .build();
    }

    public static List<ProjectDto> from(List<Project> projects) {
        return projects.stream()
                .map(ProjectDto::from)
                .collect(Collectors.toList());
    }

}
