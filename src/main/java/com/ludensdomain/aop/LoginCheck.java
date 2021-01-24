package com.ludensdomain.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginCheck {

    AuthLevel authLevel();

    enum AuthLevel {
        ADMIN, COMPANY, USER;

        public static AuthLevel getAuthLevel(String level) {

            return Enum.valueOf(AuthLevel.class, level);
        }
    }
}
