package com.ludensdomain.aop;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AuthLevel {
    ADMIN("1", "관리자"),
    COMPANY("2", "기업"),
    USER("3", "사용자");

    private final String role;
    private final String roleName;

}
