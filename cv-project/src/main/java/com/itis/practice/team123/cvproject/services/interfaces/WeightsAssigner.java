package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.dto.WeightedWorkDto;
import com.itis.practice.team123.cvproject.models.Work;

import java.util.List;

public interface WeightsAssigner {
    List<WeightedWorkDto> assignWeights(List<Work> works);
}
