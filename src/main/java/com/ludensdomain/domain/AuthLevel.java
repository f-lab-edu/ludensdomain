package com.ludensdomain.domain;

public enum AuthLevel {

    ADMIN, COMPANY, USER;

    public static AuthLevel getAuthLevel(String level) {

        return Enum.valueOf(AuthLevel.class, level);
    }
}
