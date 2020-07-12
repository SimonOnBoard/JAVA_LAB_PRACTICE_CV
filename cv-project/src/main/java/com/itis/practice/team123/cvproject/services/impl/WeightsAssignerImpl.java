package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.WeightedWorkDto;
import com.itis.practice.team123.cvproject.models.Work;
import com.itis.practice.team123.cvproject.services.interfaces.WeightsAssigner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WeightsAssignerImpl implements WeightsAssigner {
    @Value("${tags.number}")
    private Integer tagsNumber;

    @Override
    public List<WeightedWorkDto> assignWeights(List<Work> works) {
        List<WeightedWorkDto> weightedWorks = new ArrayList<>();
        for (Work work: works) {
            double weight = work.getTags().size() * 1.0 / tagsNumber;
            weightedWorks.add(new WeightedWorkDto(work, weight));
        }
        weightedWorks.sort((a, b) -> a.getWeight().compareTo(b.getWeight()));
        Collections.reverse(weightedWorks);
        return weightedWorks;
    }
}
