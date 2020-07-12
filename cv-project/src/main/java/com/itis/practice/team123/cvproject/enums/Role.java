package com.itis.practice.team123.cvproject.enums;

import lombok.Data;

public enum Role {
    STUDENT("STUDENT"), TEACHER("TEACHER"), COMPANY("COMPANY"), ADMIN("ADMIN");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
