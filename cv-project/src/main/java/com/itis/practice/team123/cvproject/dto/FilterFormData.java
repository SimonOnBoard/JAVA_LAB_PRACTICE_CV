package com.itis.practice.team123.cvproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterFormData {
    private List<String> comp;
    private List<String> education;
    private List<String> language;

}
