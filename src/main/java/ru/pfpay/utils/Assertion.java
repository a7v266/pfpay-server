package ru.pfpay.utils;

import java.math.BigDecimal;

public class Assertion {


    public static void assertNotNull(Object value) {
        assertNotNull(value, "Assert not null");
    }

    public static void assertNotNull(Object value, String message) {
        if (value == null) {
            throw new AssertionException(value, message);
        }
    }

    public static void assertNotNegative(BigDecimal value, String message) {
        if (value == null) {
            return;
        }
        if (DecimalUtils.lessZero(value)) {
            throw new AssertionException(value, message);
        }
    }

    public static void assertNotNegative(Integer value, String message) {
        if (value == null) {
            return;
        }
        if (value < 0) {
            throw new AssertionException(value, message);
        }
    }


    public static void assertPositive(BigDecimal value, String message) {
        if (value == null) {
            return;
        }
        if (DecimalUtils.lessOrEqualZero(value)) {
            throw new AssertionException(value, message);
        }
    }

    public static void assertPositive(Integer value, String message) {
        if (value == null) {
            return;
        }
        if (value <= 0) {
            throw new AssertionException(value, message);
        }
    }

}
