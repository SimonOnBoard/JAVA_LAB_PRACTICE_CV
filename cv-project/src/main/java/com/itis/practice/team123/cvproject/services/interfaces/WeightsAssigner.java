package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.WeightedStudentDto;
import com.itis.practice.team123.cvproject.dto.WeightedWorkDto;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.models.Work;

import java.util.List;

public interface WeightsAssigner {
    List<WeightedWorkDto> assignWorkWeights(List<Work> works);
    List<WeightedStudentDto> assignStudentWeightsByTags(List<Student> students, List<Tag> tags);
}
