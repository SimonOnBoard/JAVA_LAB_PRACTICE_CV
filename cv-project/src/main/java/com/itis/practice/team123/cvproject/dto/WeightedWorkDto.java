package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.models.Work;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeightedWorkDto {
    private Work work;
    private Double weight;
}
