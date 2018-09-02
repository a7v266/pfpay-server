package ru.pfpay.utils;

import java.math.BigInteger;

public class NumberUtils {

    public static Integer getInteger(Object source) {

        if (source instanceof Integer) {
            return (Integer) source;
        }

        if (source instanceof String) {
            return parseInteger((String) source);
        }

        if (source instanceof Long) {
            return ((Long) source).intValue();
        }

        return null;
    }

    public static Long getLong(Object object) {

        if (object == null) {
            return null;
        }

        if (object instanceof Long) {
            return (Long) object;
        }

        if (object instanceof Integer) {
            return new Long((Integer) object);
        }

        if (object instanceof BigInteger) {
            return ((BigInteger) object).longValue();
        }

        if (object instanceof String) {
            return parseLong((String) object);
        }

        return null;
    }


    public static Double getDouble(Object source) {

        if (source instanceof Double) {
            return (Double) source;
        }

        if (source instanceof String) {
            return getDouble((String) source);
        }

        return null;
    }


    public static Double getDouble(String source) {

        if (StringUtils.isEmpty(source)) {
            return null;
        }

        try {

            return Double.valueOf(source);

        } catch (NumberFormatException exception) {
            return null;
        }
    }

    public static double getNativeDouble(Object source) {

        Double value = getDouble(source);

        return value != null ? value : 0;
    }

    public static boolean equals(Integer first, Integer second) {
        if (first == null || second == null) {
            return false;
        }
        return first.equals(second);
    }

    public static boolean greaterZero(Long value) {
        if (value == null) {
            return false;
        }
        return value > 0;
    }

    public static Long add(Long... values) {
        long result = 0L;
        if (ArrayUtils.isNotEmpty(values)) {
            for (Long value : values) {
                if (value != null) {
                    result += value;
                }
            }
        }
        return result;
    }


    public static Integer parseInteger(String source) {

        if (StringUtils.isEmpty(source)) {

            return null;
        }

        try {

            return Integer.valueOf(source);

        } catch (NumberFormatException exception) {

            return null;
        }
    }

    public static Long parseLong(String source) {

        if (StringUtils.isEmpty(source)) {

            return null;
        }

        try {

            return Long.valueOf(source);

        } catch (NumberFormatException exception) {

            return null;
        }
    }
}
