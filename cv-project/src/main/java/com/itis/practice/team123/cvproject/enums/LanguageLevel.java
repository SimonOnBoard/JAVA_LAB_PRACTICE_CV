package com.itis.practice.team123.cvproject.enums;


public enum LanguageLevel {
    A1("A1"), A2("A2"), B1("B1"), B2("B2"), C1("C1"), C2("C2");

    private String description;

    LanguageLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
