package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.models.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDto {
    private String name;
    private String description;
    private String address;
    private String phone;
    private String mail;

    public static CompanyDto from(Company user) {
        return CompanyDto.builder()
                .address(user.getAddress())
                .description(user.getDescription())
                .phone(user.getPhone())
                .name(user.getName())
                .mail(user.getEmail())
                .build();
    }
}
