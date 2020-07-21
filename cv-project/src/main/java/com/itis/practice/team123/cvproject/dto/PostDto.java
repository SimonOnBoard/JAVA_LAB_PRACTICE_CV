package com.itis.practice.team123.cvproject.dto;


import com.itis.practice.team123.cvproject.models.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String title;
    private String description;
    public static PostDto from(Post post){
        return PostDto.builder().description(post.getDescription()).title(post.getName()).build();
    }
}
