package com.itis.practice.team123.cvproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BearerDto {
    private String bearer;
    private WorkDto workDto;
}
