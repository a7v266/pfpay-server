package ru.pfpay.utils;

public class AssertionException extends RuntimeException {

    private static final String MESSAGE_FORMAT = "%s (%s)";

    AssertionException(Object value, String message) {
        super(String.format(MESSAGE_FORMAT, message, String.valueOf(value)));
    }

}
