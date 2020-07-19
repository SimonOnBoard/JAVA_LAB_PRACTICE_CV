package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.models.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoleIdDto {
    private Long id;
    private String role;

    public static RoleIdDto from(User user) {
        return RoleIdDto.builder().role(user.getRole().toString()).id(user.getId()).build();
    }
}
