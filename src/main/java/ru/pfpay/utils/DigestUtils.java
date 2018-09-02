package ru.pfpay.utils;

import java.security.MessageDigest;

import org.slf4j.LoggerFactory;

public class DigestUtils {

    public static String createSHA256(String source) {
        StringBuilder builder = new StringBuilder();
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            for (byte value : sha256.digest(source.getBytes("UTF-8"))) {
                builder.append(String.format("%02x", value));
            }

        } catch (Exception exception) {
            LoggerFactory.getLogger(DigestUtils.class).error(exception.getMessage(), exception);
        }
        return builder.toString();
    }
}