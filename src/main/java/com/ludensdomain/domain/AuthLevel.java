package com.ludensdomain.domain;

public enum AuthLevel {

    ADMIN("1"), COMPANY("2"), USER("3");

    public final String value;

    AuthLevel(String value) {
        this.value = value;
    }

    public static AuthLevel authLevelByRole(String value) {
        switch (value) {
            case "1": return ADMIN;
            case "2": return COMPANY;
            case "3": return USER;
            default: return null;
        }
    }
}
