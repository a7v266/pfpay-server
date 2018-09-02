package ru.pfpay.utils;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class PasswordEncoderUtils {

    public static final PasswordEncoder PASSWORD_ENCODER = new StandardPasswordEncoder();


    public static String encode(String rawPassword) {
        return PASSWORD_ENCODER.encode(rawPassword);
    }

    public static boolean match(String rawPassword, String encodedPassword) {
        return PASSWORD_ENCODER.matches(rawPassword, encodedPassword);
    }
}
