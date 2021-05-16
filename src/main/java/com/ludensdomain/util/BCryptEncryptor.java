package com.ludensdomain.util;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptEncryptor {
    public static String encrypt(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean isMatch(String password, String encryptedPassword) {

        return BCrypt.checkpw(password, encryptedPassword);
    }
}
