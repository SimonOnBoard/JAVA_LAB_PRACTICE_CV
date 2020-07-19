package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.WeightedStudentDto;
import com.itis.practice.team123.cvproject.dto.WeightedWorkDto;
import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.repositories.TagsRepository;
import com.itis.practice.team123.cvproject.repositories.WorksRepository;
import com.itis.practice.team123.cvproject.services.interfaces.StudentsService;
import com.itis.practice.team123.cvproject.services.interfaces.WeightsAssigner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WeightsAssignerImpl implements WeightsAssigner {
    private final TagsRepository tagsRepository;
    private final WorksRepository worksRepository;

    public WeightsAssignerImpl(TagsRepository tagsRepository, WorksRepository worksRepository) {
        this.tagsRepository = tagsRepository;
        this.worksRepository = worksRepository;
    }

    @Override
    public List<WeightedWorkDto> assignWorkWeights(List<Work> works) {
        List<WeightedWorkDto> weightedWorks = new ArrayList<>();
        for (Work work : works) {
            double weight = work.getTags().size();
            weightedWorks.add(new WeightedWorkDto(work, weight));
        }
        weightedWorks.sort((a, b) -> a.getWeight().compareTo(b.getWeight()));
        Collections.reverse(weightedWorks);
        return weightedWorks;
    }

    @Override
    public List<WeightedStudentDto> assignStudentWeightsByTags(List<Student> students, List<Tag> tags) {
        List<WeightedStudentDto> weightedStudents = new ArrayList<>();
        for(Student student : students) {
            WeightedStudentDto weightedStudent = new WeightedStudentDto();
            weightedStudent.setWeight(0.0);
            weightedStudent.setStudent(student);
            List<Work> works;
            for (Tag tag : tags) {
                double tagSum = 0.0;
                works = worksRepository.getWorksByStudentAndTags(student, tag);
                if (works.size() == 0) continue;
                if (weightedStudent.getWeight() == 0.0) weightedStudent.setWeight(1.0);
                List<WeightedWorkDto> weightedWorks = assignWorkWeights(works);
                for (WeightedWorkDto weightedWork : weightedWorks) {
                    tagSum = tagSum + weightedWork.getWeight();
                }
                if (tagSum == 0) {
                    continue;
                }
                weightedStudent.setWeight(weightedStudent.getWeight() * tagSum);
            }
            weightedStudents.add(weightedStudent);
        }
        weightedStudents.sort((a, b) -> a.getWeight().compareTo(b.getWeight()));
        Collections.reverse(weightedStudents);
        return weightedStudents;
    }
}
