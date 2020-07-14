package com.itis.practice.team123.cvproject.dto;

import com.itis.practice.team123.cvproject.models.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LanguageDto {
    private String name;
    private String level;

    public static LanguageDto from(Language language) {
        return LanguageDto.builder()
                .name(language.getLanguage())
                .level(language.getLevel().name())
                .build();
    }

    public static List<LanguageDto> from(List<Language> languages) {
        return languages.stream()
                .map(LanguageDto::from)
                .collect(Collectors.toList());
    }
}
