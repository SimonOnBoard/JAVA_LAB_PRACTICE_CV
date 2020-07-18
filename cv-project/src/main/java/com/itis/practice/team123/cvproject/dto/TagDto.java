package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.models.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagDto {
    private String name;

    public static TagDto from(Tag tag) {
        return TagDto
                .builder()
                .name(tag.getName())
                .build();
    }
}
