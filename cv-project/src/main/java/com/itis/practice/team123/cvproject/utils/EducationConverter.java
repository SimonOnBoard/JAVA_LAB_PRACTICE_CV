package com.itis.practice.team123.cvproject.utils;

import com.itis.practice.team123.cvproject.dto.EducationDto;
import com.itis.practice.team123.cvproject.enums.Education;

public class EducationConverter {

    public static Education convert(EducationDto educationDto) {
        String name = educationDto.getName();
        switch (name) {
            case "Бакалавр неоконченное": return Education.UNFINISHED_BACHELOR;
            case "Бакалавр": return Education.BACHELOR;
            case "Магистр неоконченное": return Education.UNFINISHED_MASTER;
            case "Магистр": return Education.MASTER;
            case "Аспирант неоконченное": return Education.UNFINISHED_GRADUATE;
            case "Аспирант": return Education.GRADUATE;
            default: throw new IllegalArgumentException("Invalid or null value");
        }
    }
}
