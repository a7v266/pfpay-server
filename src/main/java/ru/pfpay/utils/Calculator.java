package ru.pfpay.utils;

import java.math.BigDecimal;
import java.util.Arrays;

public class Calculator {

    public static <T> T add(T... values) {
        if (values == null) {
            return null;
        }
        if (ClassUtils.instanceOf(values, Integer.class)) {
            return (T) add(Arrays.copyOf(values, values.length, Integer[].class));
        }
        if (ClassUtils.instanceOf(values, Long.class)) {
            return (T) add(Arrays.copyOf(values, values.length, Long[].class));
        }
        if (ClassUtils.instanceOf(values, BigDecimal.class)) {
            return (T) add(Arrays.copyOf(values, values.length, BigDecimal[].class));
        }
        return null;
    }

    public static Integer add(Integer... values) {
        int result = 0;
        for (Integer value : values) {
            if (value != null) {
                result += value;
            }
        }
        return result;
    }

    public static Long add(Long... values) {
        long result = 0L;
        for (Long value : values) {
            if (value != null) {
                result += value;
            }
        }
        return result;
    }

    public static BigDecimal add(BigDecimal... values) {
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal value : values) {
            if (value != null) {
                result = result.add(value);
            }
        }
        return result;
    }
}
