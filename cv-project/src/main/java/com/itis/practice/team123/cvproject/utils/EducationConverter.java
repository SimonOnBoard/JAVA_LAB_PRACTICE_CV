package com.itis.practice.team123.cvproject.utils;

import com.itis.practice.team123.cvproject.dto.EducationDto;
import com.itis.practice.team123.cvproject.enums.Education;

public class EducationConverter {
    private static final String UNFINISHED_BACHELOR = "Бакалавр неоконченное";
    private static final String BACHELOR =  "Бакалавр";
    private static final String UNFINISHED_MASTER = "Магистр неоконченное";
    private static final String MASTER = "Магистр";
    private static final String UNFINISHED_GRADUATE = "Аспирант неоконченное";
    private static final String GRADUATE = "Аспирант";

    public static Education convert(EducationDto educationDto) {
        String name = educationDto.getName();
        switch (name) {
            case UNFINISHED_BACHELOR: return Education.UNFINISHED_BACHELOR;
            case BACHELOR: return Education.BACHELOR;
            case UNFINISHED_MASTER: return Education.UNFINISHED_MASTER;
            case MASTER: return Education.MASTER;
            case UNFINISHED_GRADUATE: return Education.UNFINISHED_GRADUATE;
            case GRADUATE: return Education.GRADUATE;
            default: throw new IllegalArgumentException("Invalid or null value");
        }
    }

    public static EducationDto convert(Education education) {
        switch (education) {
            case UNFINISHED_BACHELOR: return EducationDto.builder()
                    .name(UNFINISHED_BACHELOR)
                    .build();
            case BACHELOR: return EducationDto.builder()
                    .name(BACHELOR)
                    .build();
            case UNFINISHED_MASTER: return EducationDto.builder()
                    .name(UNFINISHED_MASTER)
                    .build();
            case MASTER: return EducationDto.builder()
                    .name(MASTER)
                    .build();
            case UNFINISHED_GRADUATE: return EducationDto.builder()
                    .name(UNFINISHED_GRADUATE)
                    .build();
            case GRADUATE: return EducationDto.builder()
                    .name(GRADUATE)
                    .build();
            default: throw new IllegalArgumentException("Invalid or null value");
        }
    }
}
