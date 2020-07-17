package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.enums.Role;
import com.itis.practice.team123.cvproject.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Role role;
    public static UserDto from(User userToReturn) {
        return UserDto.builder()
                .id(userToReturn.getId())
                .name(userToReturn.getUsername())
                .email(userToReturn.getEmail())
                .role(userToReturn.getRole())
                .build();

    }
}
